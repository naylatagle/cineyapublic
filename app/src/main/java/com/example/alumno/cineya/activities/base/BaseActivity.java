package com.example.alumno.cineya.activities.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.AnimRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.alumno.cineya.R;
import com.example.alumno.cineya.control.IFragmentControl;

public abstract class BaseActivity extends AppCompatActivity implements IFragmentControl {

    AlertDialog mAlertDialog;

    enum Anim{
        RIGHT, LEFT
    }

    protected abstract void setContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        mAlertDialog = new ProgressDialog.Builder(BaseActivity.this)
                .setCancelable(false)
                .setMessage(R.string.loading_message)
                .create();


    }


    public void showLoading(){
        if(mAlertDialog!=null && !mAlertDialog.isShowing())
            mAlertDialog.show();
    }


    public void hideLoading(){
        if(mAlertDialog!=null)
            mAlertDialog.hide();
    }

    /*FRAGMENT TRANSACTIONS*/

    protected FragmentTransaction getTransaction() {
        return this.getSupportFragmentManager().beginTransaction();
    }

    private FragmentTransaction getTransactionAnim(@AnimRes int enter, @AnimRes int exit) {
        return getTransaction().setCustomAnimations(enter, exit, R.anim.fragment_enter_from_left, R.anim.fragment_exit_to_right);
    }

    protected void commitTransactionFragment(FragmentTransaction transaction, Fragment fragment) {
        transaction.replace(getFragmentContainer(), fragment, fragment.getClass().getName()).commit();
    }


    protected void commitAddTransactionFragment(FragmentTransaction transaction, Fragment fragment) {
        transaction.add(getFragmentContainer(), fragment, fragment.getClass().getName()).addToBackStack(fragment.getClass().getName()).commit();

    }

    public void goToFragment(Fragment fragment) {
        goToFragment(fragment, false);
        commitTransactionFragment(getTransaction(), fragment);
    }

    public void goToFragment(Fragment fragment, Boolean addBackStack) {
        if (addBackStack)
            commitAddTransactionFragment(getTransaction(), fragment);
        else
            commitTransactionFragment(getTransaction(), fragment);
    }

    public void goToFragment(Fragment fragment, Anim anim) {
        goToFragment(fragment, anim, false);
    }

    public void goToFragment(Fragment fragment, Anim anim, Boolean addBackStack) {
        FragmentTransaction transaction = null;
        switch (anim) {
            case LEFT:
                transaction = getTransactionAnim(R.anim.fragment_enter_from_left, R.anim.fragment_exit_to_right);
                break;
            case RIGHT:
                transaction = getTransactionAnim(R.anim.fragment_enter_from_right, R.anim.fragment_exit_to_left);
                break;
        }
        if (addBackStack)
            commitAddTransactionFragment(transaction, fragment);
        else
            commitTransactionFragment(transaction, fragment);
    }

    protected int getFragmentContainer() {
        return R.id.container;
    }

    @Override
    public void goBack() {
        if (this.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            this.getSupportFragmentManager().popBackStackImmediate();
        } else
            finish();

    }

    /*FRAGMENT TRANSACTIONS*/

}
