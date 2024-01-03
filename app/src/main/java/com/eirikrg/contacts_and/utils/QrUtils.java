package com.eirikrg.contacts_and.utils;

import android.graphics.Bitmap;
import com.eirikrg.domain.entities.user.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrUtils {

  public static Bitmap generateVcardQrBitmap(User user) {
    String vCardString =
        "BEGIN:VCARD\n"
            + "VERSION:3.0\n"
            + "FN:"
            + user.getName().getFullName()
            + "\n"
            + "TEL:"
            + user.getCell()
            + "\n"
            + "EMAIL:"
            + user.getEmail()
            + "\n"
            + "END:VCARD";

    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    try {
      BitMatrix bitMatrix = multiFormatWriter.encode(vCardString, BarcodeFormat.QR_CODE, 700, 700);
      BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
      return barcodeEncoder.createBitmap(bitMatrix);
    } catch (WriterException e) {
      e.printStackTrace();
      return null;
    }
  }
}
