package com.eirikrg.contacts_and.ui.activities.user_detail_activity;

import static com.eirikrg.contacts_and.utils.Constants.PERMISSIONS_REQUEST_READ_CONTACTS;
import static com.eirikrg.contacts_and.utils.QrUtils.generateVcardQrBitmap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.R;
import com.eirikrg.contacts_and.ui.activities.BaseActivity;
import com.eirikrg.contacts_and.utils.Constants;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserDetailedActivity extends AppCompatActivity implements BaseActivity {

    private UserDetailedActivityViewModel userDetailedActivityViewModel;

    private User user;
    private MaterialToolbar toolbar;
    private ImageView imageViewUser;
    private TextView textViewUserName;
    private MaterialButton buttonQr;
    private TextView textViewPhone;
    private TextView textViewCell;
    private TextView textViewEmail;
    private TextView textViewAddress;
    private TextView textViewCountry;
    private FloatingActionButton floatingActionButtonUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detailed);

        this.userDetailedActivityViewModel = new ViewModelProvider(this).get(UserDetailedActivityViewModel.class);

        Intent intent = getIntent();
        this.user = (User) intent.getSerializableExtra(Constants.INTENT_DATA_USER);

        this.toolbar = findViewById(R.id.topAppBar);
        this.imageViewUser = findViewById(R.id.iv_user);
        this.textViewUserName = findViewById(R.id.tv_userName);
        this.buttonQr = findViewById(R.id.btn_qr);
        this.textViewPhone = findViewById(R.id.tv_phone);
        this.textViewCell = findViewById(R.id.tv_cell);
        this.textViewEmail = findViewById(R.id.tv_mail);
        this.textViewAddress = findViewById(R.id.tv_address);
        this.textViewCountry = findViewById(R.id.tv_country);
        this.floatingActionButtonUser = findViewById(R.id.fab_addUser);

        initializeListeners();

        initializeObservers();

        setUserData();
    }

    private void setUserData() {
        Picasso.get().load(this.user.getPicture().getLarge()).into(imageViewUser);
        this.textViewUserName.setText(this.user.getName().getFullName());
        this.textViewPhone.setText(this.user.getPhone());
        this.textViewCell.setText(this.user.getCell());
        this.textViewEmail.setText(this.user.getEmail());
        this.textViewAddress.setText(this.user.getLocation().getFullAddress());
        this.textViewCountry.setText(this.user.getLocation().getCountry());
    }

    private void saveContacts() {
        if (checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            this.userDetailedActivityViewModel.addContact(this.user);
        }
    }

    private void showAddContactConfirmationDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.add_contact_title))
                .setMessage(getString(R.string.want_add_contact_phone_book))
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.dismiss())
                .setPositiveButton(getString(R.string.save), (dialog, which) -> saveContacts())
                .show();
    }

    private void generateQRCode() {
        View alertDialogQr = getLayoutInflater().inflate(R.layout.alert_dialog_qr, null);
        ImageView imageViewQr = alertDialogQr.findViewById(R.id.iv_qr);
        imageViewQr.setImageBitmap(generateVcardQrBitmap(user));

        new MaterialAlertDialogBuilder(UserDetailedActivity.this)
                .setView(alertDialogQr)
                .show();
    }

    @Override
    public void initializeObservers() {
        this.userDetailedActivityViewModel.getContactSaved().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean saved) {
                if (saved) {
                    Toast.makeText(getApplicationContext(), getString(R.string.contact_saved), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.contact_not_saved), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initializeListeners() {
        this.toolbar.setNavigationOnClickListener(view -> finish());
        this.floatingActionButtonUser.setOnClickListener(v -> {
            showAddContactConfirmationDialog();
        });
        this.buttonQr.setOnClickListener(v -> {
            generateQRCode();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveContacts();
            } else {
                Toast.makeText(this, getString(R.string.permission_not_accepted), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.userDetailedActivityViewModel = null;
    }
}