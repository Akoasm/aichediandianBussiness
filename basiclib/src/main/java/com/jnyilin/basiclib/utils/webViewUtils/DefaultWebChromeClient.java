package com.jnyilin.basiclib.utils.webViewUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.jnyilin.basiclib.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * @author HRR
 * @datetime 2017/11/28
 * @describe 默认WebChromeClient
 * @modifyRecord
 */

public class DefaultWebChromeClient extends WebChromeClient {
    private ValueCallback<Uri> mUploadMessage;// 表单的数据信息
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private final static int FILECHOOSER_RESULTCODE = 1;// 表单的结果回调</span>
    private Uri imageUri;
    private Context mC;
    private DefaultWebChromeClientHelper helper;

    public DefaultWebChromeClient(Context mC) {
        this.mC = mC;
        helper=new DefaultWebChromeClientHelper(mC);
    }
;    @Override
    public boolean onShowFileChooser(WebView webView,
                                     ValueCallback<Uri[]> filePathCallback,
                                     FileChooserParams fileChooserParams) {

        mUploadCallbackAboveL = filePathCallback;
        helper.chooseImage();
        return true;
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {

        mUploadMessage = uploadMsg;
        helper.chooseImage();
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

        mUploadMessage = uploadMsg;
        helper.chooseImage();
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {

        mUploadMessage = uploadMsg;
        helper.chooseImage();
    }

    public class DefaultWebChromeClientHelper {
            private Context context;

            public DefaultWebChromeClientHelper(Context context) {
                this.context = context;
            }

        private void chooseImage() {

            File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyApp");
            // Create the storage directory if it does not exist
            if (!imageStorageDir.exists()) {
                imageStorageDir.mkdirs();
            }
            File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
            imageUri = Uri.fromFile(file);

            final List<Intent> cameraIntents = new ArrayList<Intent>();
            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            final PackageManager packageManager = context.getPackageManager();
            final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
            for (ResolveInfo res : listCam) {
                final String packageName = res.activityInfo.packageName;
                final Intent i = new Intent(captureIntent);
                i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                i.setPackage(packageName);
                i.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                cameraIntents.add(i);

            }
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            Intent chooserIntent = Intent.createChooser(i, "选择照片");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
            ((BaseActivity)context).startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
        }

            public void onActivityResult(int requestCode, int resultCode, Intent data) {

                try {
                    if (requestCode == FILECHOOSER_RESULTCODE) {
                        if (null == mUploadMessage && null == mUploadCallbackAboveL) {
                            return;
                        }
                        Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
                        if (mUploadCallbackAboveL != null) {
                            onActivityResultAboveL(requestCode, resultCode, data);
                        } else if (mUploadMessage != null) {

                            if (result != null) {
                                String path = getPath(context, result);
                                Uri uri = Uri.fromFile(new File(path));
                                mUploadMessage
                                        .onReceiveValue(uri);
                            } else {
                                mUploadMessage.onReceiveValue(imageUri);
                            }
                            mUploadMessage = null;

                        }
                    } else {
                        if (mUploadMessage != null) {
                            mUploadMessage.onReceiveValue(null);
                            mUploadMessage = null;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {

                try {
                    if (requestCode != FILECHOOSER_RESULTCODE) {
                        mUploadCallbackAboveL.onReceiveValue(null);
                        mUploadCallbackAboveL = null;
                        return;
                    }

                    Uri[] results = null;

                    if (resultCode == RESULT_OK) {

                        if (data == null) {

                            results = new Uri[]{imageUri};
                        } else {
                            String dataString = data.getDataString();
                            ClipData clipData = data.getClipData();

                            if (clipData != null) {
                                results = new Uri[clipData.getItemCount()];
                                for (int i = 0; i < clipData.getItemCount(); i++) {
                                    ClipData.Item item = clipData.getItemAt(i);
                                    results[i] = item.getUri();
                                }
                            }

                            if (dataString != null) {
                                results = new Uri[]{Uri.parse(dataString)};
                            }
                        }
                    }
                    if (results != null) {
                        mUploadCallbackAboveL.onReceiveValue(results);
                        mUploadCallbackAboveL = null;
                    } else {
                        results = new Uri[]{imageUri};
                        mUploadCallbackAboveL.onReceiveValue(null);
                        mUploadCallbackAboveL = null;
                    }

                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "失败", Toast.LENGTH_SHORT);
                }
            }

            @SuppressLint("NewApi")
            public String getPath(final Context context, final Uri uri) {
                try {
                    final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

                    // DocumentProvider
                    if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                        // ExternalStorageProvider
                        if (isExternalStorageDocument(uri)) {
                            final String docId = DocumentsContract.getDocumentId(uri);
                            final String[] split = docId.split(":");
                            final String type = split[0];

                            if ("primary".equalsIgnoreCase(type)) {
                                return Environment.getExternalStorageDirectory() + "/" + split[1];
                            }

                            // TODO handle non-primary volumes
                        }
                        // DownloadsProvider
                        else if (isDownloadsDocument(uri)) {

                            final String id = DocumentsContract.getDocumentId(uri);
                            final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                            return getDataColumn(context, contentUri, null, null);
                        }
                        // MediaProvider
                        else if (isMediaDocument(uri)) {
                            final String docId = DocumentsContract.getDocumentId(uri);
                            final String[] split = docId.split(":");
                            final String type = split[0];

                            Uri contentUri = null;
                            if ("image".equals(type)) {
                                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            } else if ("video".equals(type)) {
                                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            } else if ("audio".equals(type)) {
                                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                            }

                            final String selection = "_id=?";
                            final String[] selectionArgs = new String[]{split[1]};

                            return getDataColumn(context, contentUri, selection, selectionArgs);
                        }
                    }
                    // MediaStore (and general)
                    else if ("content".equalsIgnoreCase(uri.getScheme())) {
                        return getDataColumn(context, uri, null, null);
                    }
                    // File
                    else if ("file".equalsIgnoreCase(uri.getScheme())) {
                        return uri.getPath();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "失败", Toast.LENGTH_SHORT);
                }
                return null;
            }

            /**
             * Get the value of the data column for this Uri. This is useful for
             * MediaStore Uris, and other file-based ContentProviders.
             *
             * @param context       The context.
             * @param uri           The Uri to query.
             * @param selection     (Optional) Filter used in the query.
             * @param selectionArgs (Optional) Selection arguments used in the query.
             * @return The value of the _data column, which is typically a file path.
             */
            public String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
                Cursor cursor = null;
                final String column = "_data";
                final String[] projection = {column};

                try {
                    cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        final int column_index = cursor.getColumnIndexOrThrow(column);
                        return cursor.getString(column_index);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                return null;
            }


            /**
             * @param uri The Uri to check.
             * @return Whether the Uri authority is ExternalStorageProvider.
             */
            public boolean isExternalStorageDocument(Uri uri) {
                return "com.android.externalstorage.documents".equals(uri.getAuthority());
            }


            /**
             * @param uri The Uri to check.
             * @return Whether the Uri authority is DownloadsProvider.
             */
            public boolean isDownloadsDocument(Uri uri) {
                return "com.android.providers.downloads.documents".equals(uri.getAuthority());
            }


            /**
             * @param uri The Uri to check.
             * @return Whether the Uri authority is MediaProvider.
             */
            public boolean isMediaDocument(Uri uri) {
                return "com.android.providers.media.documents".equals(uri.getAuthority());
            }
        }
}


