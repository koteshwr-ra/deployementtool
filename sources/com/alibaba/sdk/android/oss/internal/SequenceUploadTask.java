package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.OSSSharedPreferences;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SequenceUploadTask extends BaseMultipartUploadTask<ResumableUploadRequest, ResumableUploadResult> implements Callable<ResumableUploadResult> {
    private List<Integer> mAlreadyUploadIndex = new ArrayList();
    private File mCRC64RecordFile;
    private long mFirstPartSize;
    private File mRecordFile;
    private OSSSharedPreferences mSp = OSSSharedPreferences.instance(this.mContext.getApplicationContext());

    public SequenceUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext, InternalRequestOperation internalRequestOperation) {
        super(internalRequestOperation, resumableUploadRequest, oSSCompletedCallback, executionContext);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0180 A[Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initMultipartUploadId() throws java.io.IOException, com.alibaba.sdk.android.oss.ClientException, com.alibaba.sdk.android.oss.ServiceException {
        /*
            r13 = this;
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r0 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r0 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r0
            java.lang.String r0 = r0.getRecordDirectory()
            boolean r0 = com.alibaba.sdk.android.oss.common.utils.OSSUtils.isEmptyString(r0)
            r1 = 0
            if (r0 != 0) goto L_0x0233
            android.net.Uri r0 = r13.mUploadUri
            if (r0 == 0) goto L_0x003a
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r13.mContext
            android.content.Context r0 = r0.getApplicationContext()
            android.content.ContentResolver r0 = r0.getContentResolver()
            android.net.Uri r2 = r13.mUploadUri
            java.lang.String r3 = "r"
            android.os.ParcelFileDescriptor r0 = r0.openFileDescriptor(r2, r3)
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = com.alibaba.sdk.android.oss.common.utils.BinaryUtil.calculateMd5Str((java.io.FileDescriptor) r2)     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0040
            r0.close()
            goto L_0x0040
        L_0x0033:
            r1 = move-exception
            if (r0 == 0) goto L_0x0039
            r0.close()
        L_0x0039:
            throw r1
        L_0x003a:
            java.lang.String r0 = r13.mUploadFilePath
            java.lang.String r2 = com.alibaba.sdk.android.oss.common.utils.BinaryUtil.calculateMd5Str((java.lang.String) r0)
        L_0x0040:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r2 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r2
            java.lang.String r2 = r2.getBucketName()
            r0.append(r2)
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r2 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r2
            java.lang.String r2 = r2.getObjectKey()
            r0.append(r2)
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r2 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r2
            long r2 = r2.getPartSize()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r0.append(r2)
            boolean r2 = r13.mCheckCRC64
            if (r2 == 0) goto L_0x0074
            java.lang.String r2 = "-crc64"
            goto L_0x0076
        L_0x0074:
            java.lang.String r2 = ""
        L_0x0076:
            r0.append(r2)
            java.lang.String r2 = "-sequence"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            byte[] r0 = r0.getBytes()
            java.lang.String r0 = com.alibaba.sdk.android.oss.common.utils.BinaryUtil.calculateMd5Str((byte[]) r0)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r3 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r3 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r3
            java.lang.String r3 = r3.getRecordDirectory()
            r2.append(r3)
            java.lang.String r3 = java.io.File.separator
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            r13.mRecordFile = r2
            boolean r0 = r2.exists()
            if (r0 == 0) goto L_0x00de
            java.io.BufferedReader r0 = new java.io.BufferedReader
            java.io.FileReader r2 = new java.io.FileReader
            java.io.File r3 = r13.mRecordFile
            r2.<init>(r3)
            r0.<init>(r2)
            java.lang.String r2 = r0.readLine()
            r13.mUploadId = r2
            r0.close()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "sequence [initUploadId] - Found record file, uploadid: "
            r0.append(r2)
            java.lang.String r2 = r13.mUploadId
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.alibaba.sdk.android.oss.common.OSSLog.logDebug(r0)
        L_0x00de:
            java.lang.String r0 = r13.mUploadId
            boolean r0 = com.alibaba.sdk.android.oss.common.utils.OSSUtils.isEmptyString(r0)
            if (r0 != 0) goto L_0x0200
            boolean r0 = r13.mCheckCRC64
            if (r0 == 0) goto L_0x013e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r2 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r2
            java.lang.String r2 = r2.getRecordDirectory()
            r0.append(r2)
            java.lang.String r2 = java.io.File.separator
            r0.append(r2)
            java.lang.String r2 = r13.mUploadId
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            boolean r0 = r2.exists()
            if (r0 == 0) goto L_0x013e
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r2)
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream
            r3.<init>(r0)
            java.lang.Object r0 = r3.readObject()     // Catch:{ ClassNotFoundException -> 0x012b }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ ClassNotFoundException -> 0x012b }
            r2.delete()     // Catch:{ ClassNotFoundException -> 0x0127 }
            goto L_0x0130
        L_0x0127:
            r4 = move-exception
            goto L_0x012d
        L_0x0129:
            r0 = move-exception
            goto L_0x0137
        L_0x012b:
            r4 = move-exception
            r0 = r1
        L_0x012d:
            com.alibaba.sdk.android.oss.common.OSSLog.logThrowable2Local(r4)     // Catch:{ all -> 0x0129 }
        L_0x0130:
            r3.close()
            r2.delete()
            goto L_0x013f
        L_0x0137:
            r3.close()
            r2.delete()
            throw r0
        L_0x013e:
            r0 = r1
        L_0x013f:
            r2 = 0
            r3 = 0
        L_0x0141:
            com.alibaba.sdk.android.oss.model.ListPartsRequest r4 = new com.alibaba.sdk.android.oss.model.ListPartsRequest
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r5 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r5 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r5
            java.lang.String r5 = r5.getBucketName()
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r6 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r6 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r6
            java.lang.String r6 = r6.getObjectKey()
            java.lang.String r7 = r13.mUploadId
            r4.<init>(r5, r6, r7)
            if (r3 <= 0) goto L_0x0161
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            r4.setPartNumberMarker(r5)
        L_0x0161:
            com.alibaba.sdk.android.oss.internal.InternalRequestOperation r5 = r13.mApiOperation
            com.alibaba.sdk.android.oss.internal.OSSAsyncTask r4 = r5.listParts(r4, r1)
            com.alibaba.sdk.android.oss.model.OSSResult r5 = r4.getResult()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            com.alibaba.sdk.android.oss.model.ListPartsResult r5 = (com.alibaba.sdk.android.oss.model.ListPartsResult) r5     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            boolean r6 = r5.isTruncated()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            int r3 = r5.getNextPartNumberMarker()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.util.List r5 = r5.getParts()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r7 = 0
        L_0x017a:
            int r8 = r5.size()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            if (r7 >= r8) goto L_0x01f9
            java.lang.Object r8 = r5.get(r7)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            com.alibaba.sdk.android.oss.model.PartSummary r8 = (com.alibaba.sdk.android.oss.model.PartSummary) r8     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            com.alibaba.sdk.android.oss.model.PartETag r9 = new com.alibaba.sdk.android.oss.model.PartETag     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            int r10 = r8.getPartNumber()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.lang.String r11 = r8.getETag()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r9.<init>(r10, r11)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            long r10 = r8.getSize()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r9.setPartSize(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            if (r0 == 0) goto L_0x01c5
            int r10 = r0.size()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            if (r10 <= 0) goto L_0x01c5
            int r10 = r9.getPartNumber()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            boolean r10 = r0.containsKey(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            if (r10 == 0) goto L_0x01c5
            int r10 = r9.getPartNumber()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.lang.Object r10 = r0.get(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            long r10 = r10.longValue()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r9.setCRC64(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
        L_0x01c5:
            java.util.List r10 = r13.mPartETags     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r10.add(r9)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            long r9 = r13.mUploadedLength     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            long r11 = r8.getSize()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            long r9 = r9 + r11
            r13.mUploadedLength = r9     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.util.List<java.lang.Integer> r9 = r13.mAlreadyUploadIndex     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            int r10 = r8.getPartNumber()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r9.add(r10)     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            if (r7 != 0) goto L_0x01e8
            long r8 = r8.getSize()     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
            r13.mFirstPartSize = r8     // Catch:{ ServiceException -> 0x01ed, ClientException -> 0x01eb }
        L_0x01e8:
            int r7 = r7 + 1
            goto L_0x017a
        L_0x01eb:
            r0 = move-exception
            throw r0
        L_0x01ed:
            r5 = move-exception
            int r6 = r5.getStatusCode()
            r7 = 404(0x194, float:5.66E-43)
            if (r6 != r7) goto L_0x01ff
            r13.mUploadId = r1
            r6 = 0
        L_0x01f9:
            r4.waitUntilFinished()
            if (r6 != 0) goto L_0x0141
            goto L_0x0200
        L_0x01ff:
            throw r5
        L_0x0200:
            java.io.File r0 = r13.mRecordFile
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0233
            java.io.File r0 = r13.mRecordFile
            boolean r0 = r0.createNewFile()
            if (r0 == 0) goto L_0x0211
            goto L_0x0233
        L_0x0211:
            com.alibaba.sdk.android.oss.ClientException r0 = new com.alibaba.sdk.android.oss.ClientException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Can't create file at path: "
            r1.append(r2)
            java.io.File r2 = r13.mRecordFile
            java.lang.String r2 = r2.getAbsolutePath()
            r1.append(r2)
            java.lang.String r2 = "\nPlease make sure the directory exist!"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0233:
            java.lang.String r0 = r13.mUploadId
            boolean r0 = com.alibaba.sdk.android.oss.common.utils.OSSUtils.isEmptyString(r0)
            if (r0 == 0) goto L_0x0285
            com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest r0 = new com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r2 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r2
            java.lang.String r2 = r2.getBucketName()
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r3 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r3 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r3
            java.lang.String r3 = r3.getObjectKey()
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r4 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r4 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r4
            com.alibaba.sdk.android.oss.model.ObjectMetadata r4 = r4.getMetadata()
            r0.<init>(r2, r3, r4)
            r2 = 1
            r0.isSequential = r2
            com.alibaba.sdk.android.oss.internal.InternalRequestOperation r2 = r13.mApiOperation
            com.alibaba.sdk.android.oss.internal.OSSAsyncTask r0 = r2.initMultipartUpload(r0, r1)
            com.alibaba.sdk.android.oss.model.OSSResult r0 = r0.getResult()
            com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult r0 = (com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult) r0
            java.lang.String r0 = r0.getUploadId()
            r13.mUploadId = r0
            java.io.File r0 = r13.mRecordFile
            if (r0 == 0) goto L_0x0285
            java.io.BufferedWriter r0 = new java.io.BufferedWriter
            java.io.FileWriter r1 = new java.io.FileWriter
            java.io.File r2 = r13.mRecordFile
            r1.<init>(r2)
            r0.<init>(r1)
            java.lang.String r1 = r13.mUploadId
            r0.write(r1)
            r0.close()
        L_0x0285:
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r0 = r13.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r0 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r0
            java.lang.String r1 = r13.mUploadId
            r0.setUploadId(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.initMultipartUploadId():void");
    }

    /* access modifiers changed from: protected */
    public ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException, InterruptedException {
        long j = this.mUploadedLength;
        checkCancel();
        int i = this.mPartAttr[0];
        int i2 = this.mPartAttr[1];
        if (this.mPartETags.size() > 0 && this.mAlreadyUploadIndex.size() > 0) {
            if (this.mUploadedLength > this.mFileLength) {
                throw new ClientException("The uploading file is inconsistent with before");
            } else if (this.mFirstPartSize == ((long) i)) {
                long j2 = this.mUploadedLength;
                if (!TextUtils.isEmpty(this.mSp.getStringValue(this.mUploadId))) {
                    j2 = Long.valueOf(this.mSp.getStringValue(this.mUploadId)).longValue();
                }
                long j3 = j2;
                if (this.mProgressCallback != null) {
                    this.mProgressCallback.onProgress(this.mRequest, j3, this.mFileLength);
                }
                this.mSp.removeKey(this.mUploadId);
            } else {
                throw new ClientException("The part size setting is inconsistent with before");
            }
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mAlreadyUploadIndex.size() == 0 || !this.mAlreadyUploadIndex.contains(Integer.valueOf(i3 + 1))) {
                if (i3 == i2 - 1) {
                    i = (int) (this.mFileLength - j);
                }
                OSSLog.logDebug("upload part readByte : " + i);
                j += (long) i;
                uploadPart(i3, i, i2);
                if (this.mUploadException != null) {
                    break;
                }
            }
        }
        checkException();
        CompleteMultipartUploadResult completeMultipartUploadResult = completeMultipartUploadResult();
        ResumableUploadResult resumableUploadResult = null;
        if (completeMultipartUploadResult != null) {
            resumableUploadResult = new ResumableUploadResult(completeMultipartUploadResult);
        }
        File file = this.mRecordFile;
        if (file != null) {
            file.delete();
        }
        File file2 = this.mCRC64RecordFile;
        if (file2 != null) {
            file2.delete();
        }
        return resumableUploadResult;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.alibaba.sdk.android.oss.model.UploadPartRequest} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.RandomAccessFile} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x011b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011c, code lost:
        r1 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0147, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x014c, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0163, code lost:
        processException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0167, code lost:
        r2 = new com.alibaba.sdk.android.oss.model.PartETag(r1.getPartNumber(), r0.getPartEtag());
        r2.setPartSize((long) r1.getPartContent().length);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x017f, code lost:
        if (r7.mCheckCRC64 != false) goto L_0x0181;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0181, code lost:
        r2.setCRC64(new java.util.zip.CheckedInputStream(new java.io.ByteArrayInputStream(r1.getPartContent()), new com.alibaba.sdk.android.oss.common.utils.CRC64()).getChecksum().getValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x019f, code lost:
        r7.mPartETags.add(r2);
        r7.mUploadedLength += (long) r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01b1, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01b6, code lost:
        r11.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01bf A[SYNTHETIC, Splitter:B:101:0x01bf] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01c7 A[Catch:{ IOException -> 0x01c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01cc A[Catch:{ IOException -> 0x01c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011b A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:33:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x013f A[SYNTHETIC, Splitter:B:70:0x013f] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0147 A[Catch:{ IOException -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x014c A[Catch:{ IOException -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0163 A[Catch:{ all -> 0x01ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0167 A[Catch:{ all -> 0x01ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01ac A[SYNTHETIC, Splitter:B:92:0x01ac] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01b1 A[Catch:{ IOException -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b6 A[Catch:{ IOException -> 0x0143 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uploadPart(int r16, int r17, int r18) {
        /*
            r15 = this;
            r7 = r15
            r0 = r16
            r8 = r17
            r1 = 0
            com.alibaba.sdk.android.oss.network.ExecutionContext r2 = r7.mContext     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            com.alibaba.sdk.android.oss.network.CancellationHandler r2 = r2.getCancellationHandler()     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            boolean r2 = r2.isCancelled()     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            if (r2 == 0) goto L_0x0013
            return
        L_0x0013:
            int r2 = r7.mRunPartTaskCount     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            r3 = 1
            int r2 = r2 + r3
            r7.mRunPartTaskCount = r2     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            r15.preUploadPart(r16, r17, r18)     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            long r4 = (long) r0     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r7.mRequest     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r2 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r2     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            long r9 = r2.getPartSize()     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            long r4 = r4 * r9
            byte[] r2 = new byte[r8]     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            android.net.Uri r6 = r7.mUploadUri     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            r9 = 0
            if (r6 == 0) goto L_0x0069
            com.alibaba.sdk.android.oss.network.ExecutionContext r6 = r7.mContext     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            android.net.Uri r10 = r7.mUploadUri     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            java.io.InputStream r6 = r6.openInputStream(r10)     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            java.io.BufferedInputStream r10 = new java.io.BufferedInputStream     // Catch:{ ServiceException -> 0x0063, Exception -> 0x005e, all -> 0x0058 }
            r10.<init>(r6)     // Catch:{ ServiceException -> 0x0063, Exception -> 0x005e, all -> 0x0058 }
            r10.skip(r4)     // Catch:{ ServiceException -> 0x0054, Exception -> 0x0051, all -> 0x004d }
            r10.read(r2, r9, r8)     // Catch:{ ServiceException -> 0x0054, Exception -> 0x0051, all -> 0x004d }
            r9 = r1
            r11 = r10
            r10 = r6
            goto L_0x007b
        L_0x004d:
            r0 = move-exception
            r2 = r0
            r11 = r10
            goto L_0x005b
        L_0x0051:
            r0 = move-exception
            r11 = r10
            goto L_0x0060
        L_0x0054:
            r0 = move-exception
            r9 = r1
            r11 = r10
            goto L_0x0066
        L_0x0058:
            r0 = move-exception
            r2 = r0
            r11 = r1
        L_0x005b:
            r10 = r6
            goto L_0x01bd
        L_0x005e:
            r0 = move-exception
            r11 = r1
        L_0x0060:
            r10 = r6
            goto L_0x013a
        L_0x0063:
            r0 = move-exception
            r9 = r1
            r11 = r9
        L_0x0066:
            r10 = r6
            goto L_0x015b
        L_0x0069:
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            java.io.File r10 = r7.mUploadFile     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            java.lang.String r11 = "r"
            r6.<init>(r10, r11)     // Catch:{ ServiceException -> 0x0157, Exception -> 0x0137, all -> 0x0131 }
            r6.seek(r4)     // Catch:{ ServiceException -> 0x012c, Exception -> 0x0127, all -> 0x0120 }
            r6.readFully(r2, r9, r8)     // Catch:{ ServiceException -> 0x012c, Exception -> 0x0127, all -> 0x0120 }
            r10 = r1
            r11 = r10
            r9 = r6
        L_0x007b:
            com.alibaba.sdk.android.oss.model.UploadPartRequest r12 = new com.alibaba.sdk.android.oss.model.UploadPartRequest     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r4 = r7.mRequest     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r4 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r4     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            java.lang.String r4 = r4.getBucketName()     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r5 = r7.mRequest     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r5 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r5     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            java.lang.String r5 = r5.getObjectKey()     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            java.lang.String r6 = r7.mUploadId     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            int r0 = r0 + r3
            r12.<init>(r4, r5, r6, r0)     // Catch:{ ServiceException -> 0x011e, Exception -> 0x011b }
            r12.setPartContent(r2)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            java.lang.String r0 = com.alibaba.sdk.android.oss.common.utils.BinaryUtil.calculateBase64Md5((byte[]) r2)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r12.setMd5Digest(r0)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r0 = r7.mRequest     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r0 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r0     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            java.lang.Enum r0 = r0.getCRC64()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r12.setCRC64(r0)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.internal.InternalRequestOperation r0 = r7.mApiOperation     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.UploadPartResult r0 = r0.syncUploadPart(r12)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.model.PartETag r1 = new com.alibaba.sdk.android.oss.model.PartETag     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            int r2 = r12.getPartNumber()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            java.lang.String r4 = r0.getETag()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r1.<init>(r2, r4)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            long r4 = (long) r8     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r1.setPartSize(r4)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            boolean r2 = r7.mCheckCRC64     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            if (r2 == 0) goto L_0x00ce
            java.lang.Long r0 = r0.getClientCRC()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            long r13 = r0.longValue()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r1.setCRC64(r13)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
        L_0x00ce:
            java.util.List r0 = r7.mPartETags     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r0.add(r1)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            long r13 = r7.mUploadedLength     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            long r13 = r13 + r4
            r7.mUploadedLength = r13     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r15.uploadPartFinish(r1)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r7.mContext     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.network.CancellationHandler r0 = r0.getCancellationHandler()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            boolean r0 = r0.isCancelled()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            if (r0 != 0) goto L_0x0102
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r2 = r7.mRequest     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            long r3 = r7.mUploadedLength     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            long r5 = r7.mFileLength     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r1 = r15
            r1.onProgressCallback(r2, r3, r5)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            if (r9 == 0) goto L_0x00f6
            r9.close()     // Catch:{ IOException -> 0x0143 }
        L_0x00f6:
            if (r10 == 0) goto L_0x00fb
            r10.close()     // Catch:{ IOException -> 0x0143 }
        L_0x00fb:
            if (r11 == 0) goto L_0x01b9
            r11.close()     // Catch:{ IOException -> 0x0143 }
            goto L_0x01b9
        L_0x0102:
            com.alibaba.sdk.android.oss.TaskCancelException r0 = new com.alibaba.sdk.android.oss.TaskCancelException     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            java.lang.String r1 = "sequence upload task cancel"
            r0.<init>((java.lang.String) r1)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            com.alibaba.sdk.android.oss.ClientException r1 = new com.alibaba.sdk.android.oss.ClientException     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            java.lang.String r2 = r0.getMessage()     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            r1.<init>(r2, r0, r3)     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
            throw r1     // Catch:{ ServiceException -> 0x0117, Exception -> 0x011b }
        L_0x0117:
            r0 = move-exception
            r1 = r12
            goto L_0x015b
        L_0x011b:
            r0 = move-exception
            r1 = r9
            goto L_0x013a
        L_0x011e:
            r0 = move-exception
            goto L_0x015b
        L_0x0120:
            r0 = move-exception
            r2 = r0
            r10 = r1
            r11 = r10
            r1 = r6
            goto L_0x01bd
        L_0x0127:
            r0 = move-exception
            r10 = r1
            r11 = r10
            r1 = r6
            goto L_0x013a
        L_0x012c:
            r0 = move-exception
            r10 = r1
            r11 = r10
            r9 = r6
            goto L_0x015b
        L_0x0131:
            r0 = move-exception
            r2 = r0
            r10 = r1
            r11 = r10
            goto L_0x01bd
        L_0x0137:
            r0 = move-exception
            r10 = r1
            r11 = r10
        L_0x013a:
            r15.processException(r0)     // Catch:{ all -> 0x0154 }
            if (r1 == 0) goto L_0x0145
            r1.close()     // Catch:{ IOException -> 0x0143 }
            goto L_0x0145
        L_0x0143:
            r0 = move-exception
            goto L_0x0150
        L_0x0145:
            if (r10 == 0) goto L_0x014a
            r10.close()     // Catch:{ IOException -> 0x0143 }
        L_0x014a:
            if (r11 == 0) goto L_0x01b9
            r11.close()     // Catch:{ IOException -> 0x0143 }
            goto L_0x01b9
        L_0x0150:
            com.alibaba.sdk.android.oss.common.OSSLog.logThrowable2Local(r0)
            goto L_0x01b9
        L_0x0154:
            r0 = move-exception
            r2 = r0
            goto L_0x01bd
        L_0x0157:
            r0 = move-exception
            r9 = r1
            r10 = r9
            r11 = r10
        L_0x015b:
            int r2 = r0.getStatusCode()     // Catch:{ all -> 0x01ba }
            r3 = 409(0x199, float:5.73E-43)
            if (r2 == r3) goto L_0x0167
            r15.processException(r0)     // Catch:{ all -> 0x01ba }
            goto L_0x01aa
        L_0x0167:
            com.alibaba.sdk.android.oss.model.PartETag r2 = new com.alibaba.sdk.android.oss.model.PartETag     // Catch:{ all -> 0x01ba }
            int r3 = r1.getPartNumber()     // Catch:{ all -> 0x01ba }
            java.lang.String r0 = r0.getPartEtag()     // Catch:{ all -> 0x01ba }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x01ba }
            byte[] r0 = r1.getPartContent()     // Catch:{ all -> 0x01ba }
            int r0 = r0.length     // Catch:{ all -> 0x01ba }
            long r3 = (long) r0     // Catch:{ all -> 0x01ba }
            r2.setPartSize(r3)     // Catch:{ all -> 0x01ba }
            boolean r0 = r7.mCheckCRC64     // Catch:{ all -> 0x01ba }
            if (r0 == 0) goto L_0x019f
            byte[] r0 = r1.getPartContent()     // Catch:{ all -> 0x01ba }
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x01ba }
            r1.<init>(r0)     // Catch:{ all -> 0x01ba }
            java.util.zip.CheckedInputStream r0 = new java.util.zip.CheckedInputStream     // Catch:{ all -> 0x01ba }
            com.alibaba.sdk.android.oss.common.utils.CRC64 r3 = new com.alibaba.sdk.android.oss.common.utils.CRC64     // Catch:{ all -> 0x01ba }
            r3.<init>()     // Catch:{ all -> 0x01ba }
            r0.<init>(r1, r3)     // Catch:{ all -> 0x01ba }
            java.util.zip.Checksum r0 = r0.getChecksum()     // Catch:{ all -> 0x01ba }
            long r0 = r0.getValue()     // Catch:{ all -> 0x01ba }
            r2.setCRC64(r0)     // Catch:{ all -> 0x01ba }
        L_0x019f:
            java.util.List r0 = r7.mPartETags     // Catch:{ all -> 0x01ba }
            r0.add(r2)     // Catch:{ all -> 0x01ba }
            long r0 = r7.mUploadedLength     // Catch:{ all -> 0x01ba }
            long r2 = (long) r8     // Catch:{ all -> 0x01ba }
            long r0 = r0 + r2
            r7.mUploadedLength = r0     // Catch:{ all -> 0x01ba }
        L_0x01aa:
            if (r9 == 0) goto L_0x01af
            r9.close()     // Catch:{ IOException -> 0x0143 }
        L_0x01af:
            if (r10 == 0) goto L_0x01b4
            r10.close()     // Catch:{ IOException -> 0x0143 }
        L_0x01b4:
            if (r11 == 0) goto L_0x01b9
            r11.close()     // Catch:{ IOException -> 0x0143 }
        L_0x01b9:
            return
        L_0x01ba:
            r0 = move-exception
            r2 = r0
            r1 = r9
        L_0x01bd:
            if (r1 == 0) goto L_0x01c5
            r1.close()     // Catch:{ IOException -> 0x01c3 }
            goto L_0x01c5
        L_0x01c3:
            r0 = move-exception
            goto L_0x01d0
        L_0x01c5:
            if (r10 == 0) goto L_0x01ca
            r10.close()     // Catch:{ IOException -> 0x01c3 }
        L_0x01ca:
            if (r11 == 0) goto L_0x01d3
            r11.close()     // Catch:{ IOException -> 0x01c3 }
            goto L_0x01d3
        L_0x01d0:
            com.alibaba.sdk.android.oss.common.OSSLog.logThrowable2Local(r0)
        L_0x01d3:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.uploadPart(int, int, int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkException() throws java.io.IOException, com.alibaba.sdk.android.oss.ServiceException, com.alibaba.sdk.android.oss.ClientException {
        /*
            r6 = this;
            com.alibaba.sdk.android.oss.network.ExecutionContext r0 = r6.mContext
            com.alibaba.sdk.android.oss.network.CancellationHandler r0 = r0.getCancellationHandler()
            boolean r0 = r0.isCancelled()
            if (r0 == 0) goto L_0x00c7
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r0 = r6.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r0 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r0
            java.lang.Boolean r0 = r0.deleteUploadOnCancelling()
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0026
            r6.abortThisUpload()
            java.io.File r0 = r6.mRecordFile
            if (r0 == 0) goto L_0x00c7
            r0.delete()
            goto L_0x00c7
        L_0x0026:
            java.util.List r0 = r6.mPartETags
            if (r0 == 0) goto L_0x00c7
            java.util.List r0 = r6.mPartETags
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x00c7
            boolean r0 = r6.mCheckCRC64
            if (r0 == 0) goto L_0x00c7
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r0 = r6.mRequest
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r0 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r0
            java.lang.String r0 = r0.getRecordDirectory()
            if (r0 == 0) goto L_0x00c7
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.List r1 = r6.mPartETags
            java.util.Iterator r1 = r1.iterator()
        L_0x004b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x006b
            java.lang.Object r2 = r1.next()
            com.alibaba.sdk.android.oss.model.PartETag r2 = (com.alibaba.sdk.android.oss.model.PartETag) r2
            int r3 = r2.getPartNumber()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            long r4 = r2.getCRC64()
            java.lang.Long r2 = java.lang.Long.valueOf(r4)
            r0.put(r3, r2)
            goto L_0x004b
        L_0x006b:
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b7 }
            r2.<init>()     // Catch:{ IOException -> 0x00b7 }
            com.alibaba.sdk.android.oss.model.MultipartUploadRequest r3 = r6.mRequest     // Catch:{ IOException -> 0x00b7 }
            com.alibaba.sdk.android.oss.model.ResumableUploadRequest r3 = (com.alibaba.sdk.android.oss.model.ResumableUploadRequest) r3     // Catch:{ IOException -> 0x00b7 }
            java.lang.String r3 = r3.getRecordDirectory()     // Catch:{ IOException -> 0x00b7 }
            r2.append(r3)     // Catch:{ IOException -> 0x00b7 }
            java.lang.String r3 = java.io.File.separator     // Catch:{ IOException -> 0x00b7 }
            r2.append(r3)     // Catch:{ IOException -> 0x00b7 }
            java.lang.String r3 = r6.mUploadId     // Catch:{ IOException -> 0x00b7 }
            r2.append(r3)     // Catch:{ IOException -> 0x00b7 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x00b7 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x00b7 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x00b7 }
            r6.mCRC64RecordFile = r3     // Catch:{ IOException -> 0x00b7 }
            boolean r2 = r3.exists()     // Catch:{ IOException -> 0x00b7 }
            if (r2 != 0) goto L_0x009c
            java.io.File r2 = r6.mCRC64RecordFile     // Catch:{ IOException -> 0x00b7 }
            r2.createNewFile()     // Catch:{ IOException -> 0x00b7 }
        L_0x009c:
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x00b7 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00b7 }
            java.io.File r4 = r6.mCRC64RecordFile     // Catch:{ IOException -> 0x00b7 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x00b7 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x00b7 }
            r2.writeObject(r0)     // Catch:{ IOException -> 0x00b2, all -> 0x00af }
            r2.close()
            goto L_0x00c7
        L_0x00af:
            r0 = move-exception
            r1 = r2
            goto L_0x00c1
        L_0x00b2:
            r0 = move-exception
            r1 = r2
            goto L_0x00b8
        L_0x00b5:
            r0 = move-exception
            goto L_0x00c1
        L_0x00b7:
            r0 = move-exception
        L_0x00b8:
            com.alibaba.sdk.android.oss.common.OSSLog.logThrowable2Local(r0)     // Catch:{ all -> 0x00b5 }
            if (r1 == 0) goto L_0x00c7
            r1.close()
            goto L_0x00c7
        L_0x00c1:
            if (r1 == 0) goto L_0x00c6
            r1.close()
        L_0x00c6:
            throw r0
        L_0x00c7:
            super.checkException()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.checkException():void");
    }

    /* access modifiers changed from: protected */
    public void abortThisUpload() {
        if (this.mUploadId != null) {
            this.mApiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId), (OSSCompletedCallback<AbortMultipartUploadRequest, AbortMultipartUploadResult>) null).waitUntilFinished();
        }
    }

    /* access modifiers changed from: protected */
    public void processException(Exception exc) {
        if (this.mUploadException == null || !exc.getMessage().equals(this.mUploadException.getMessage())) {
            this.mUploadException = exc;
        }
        OSSLog.logThrowable2Local(exc);
        if (this.mContext.getCancellationHandler().isCancelled() && !this.mIsCancel) {
            this.mIsCancel = true;
        }
    }

    /* access modifiers changed from: protected */
    public void uploadPartFinish(PartETag partETag) throws Exception {
        if (this.mContext.getCancellationHandler().isCancelled() && !this.mSp.contains(this.mUploadId)) {
            this.mSp.setStringValue(this.mUploadId, String.valueOf(this.mUploadedLength));
            onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
        }
    }
}
