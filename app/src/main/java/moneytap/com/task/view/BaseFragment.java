package moneytap.com.task.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moneytap.com.task.R;
import moneytap.com.task.presenter.BasePresenter;
import moneytap.com.task.utils.Constants;


public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();


    protected View rootView;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder mAlertDialogBuilder;
    private AlertDialog mAlertDialog;
    private View layout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResId(), container, false);
        return rootView;
    }


    protected abstract BasePresenter getPresenter();

    protected abstract int getLayoutResId();

    public void showProgressDialog(boolean isCancelAble) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(getActivity(), null, null);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mProgressDialog.setCancelable(isCancelAble);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.setCancelable(isCancelAble);
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
            Log.i(TAG, "hideDialog: " + e.getMessage());
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        getPresenter().start();
    }

    public void showDialog(String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.AppAlertDialogTheme);
        alertDialogBuilder.setTitle(Constants.ALERT_TITLE).setMessage(msg).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertDialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
    }


    public boolean isAlertDialogShowing(AlertDialog thisAlertDialog) {
        if (thisAlertDialog != null) {
            return thisAlertDialog.isShowing();
        }
        return false;
    }


}
