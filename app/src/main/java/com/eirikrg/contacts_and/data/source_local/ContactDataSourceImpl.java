package com.eirikrg.contacts_and.data.source_local;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.provider.ContactsContract;
import com.eirikrg.domain.entities.user.User;
import java.util.ArrayList;

public class ContactDataSourceImpl implements ContactDataSource {
  private final Context context;

  public ContactDataSourceImpl(Context context) {
    this.context = context;
  }

  private static void generateNameOperation(
      User contact, ArrayList<ContentProviderOperation> operations) {
    ContentProviderOperation.Builder builder;
    builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
    builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0);
    builder.withValue(
        ContactsContract.Data.MIMETYPE,
        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
    builder.withValue(
        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
        contact.getName().getFullName());
    operations.add(builder.build());
  }

  private static void generateCellphoneOperatrion(
      User contact, ArrayList<ContentProviderOperation> operations) {
    ContentProviderOperation.Builder builder;
    builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
    builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0);
    builder.withValue(
        ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
    builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, contact.getCell());
    builder.withValue(
        ContactsContract.CommonDataKinds.Phone.TYPE,
        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
    operations.add(builder.build());
  }

  @Override
  public boolean addContact(User contact) {
    try {
      ArrayList<ContentProviderOperation> operations = new ArrayList<>();

      ContentProviderOperation.Builder builder =
          ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
      builder.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null);
      builder.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
      operations.add(builder.build());

      // Se añade el número
      generateCellphoneOperatrion(contact, operations);

      // Se añade el nombre
      generateNameOperation(contact, operations);

      // Guardamos
      this.context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, operations);

    } catch (Exception e) {
      e.printStackTrace();
      return Boolean.FALSE;
    }

    return Boolean.TRUE;
  }
}
