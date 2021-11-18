package com.medline.tdd.day2;

public class NotifierStub implements Notifier {

  private String notificationMediumDetails;
  private String message;

  @Override
  public void sendNotification(String notificationMediumDetails, String message) {
    this.notificationMediumDetails = notificationMediumDetails;
    this.message = message;
  }

  public boolean hasRecivedMediumDetails(String mediumDetails) {
    return notificationMediumDetails.equals(mediumDetails);
  }

  public boolean hasRecivedMessage(String message) {
    return message.equals(message);
  }

}
