package com.medline.tdd.day2;

public class SMSNotifier implements Notifier {
  @Override
  public String getWelcomeMessage(String notificationMediumDetails, String message) {
    return message;
  }
}