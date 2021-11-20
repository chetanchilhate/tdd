package com.medline.tdd.day2.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.medline.tdd.day2.registration.EmailNotifier;
import com.medline.tdd.day2.registration.NotificationType;
import com.medline.tdd.day2.registration.Notifier;
import com.medline.tdd.day2.registration.NotifierFactory;
import com.medline.tdd.day2.registration.SMSNotifier;
import org.junit.jupiter.api.Test;

class NotifierFactoryTest {

  @Test
  void firstNotifierFactoryShouldBeEqualToSecondNotifierFactory() {

    //arrange
    NotifierFactory firstNotifierFactory = NotifierFactory.createInstance();
    NotifierFactory secondNotifierFactory = NotifierFactory.createInstance();

    //assert
    assertEquals(firstNotifierFactory, secondNotifierFactory);
  }

  @Test
  void weShouldGetEmailNotifierWhenNotificationTypeIsEmail() {

    //arrange
    NotifierFactory factory = NotifierFactory.createInstance();

    //act
    Notifier emailNotifier = factory.getNotifier(NotificationType.EMAIL);

    //assert
    assertTrue(emailNotifier instanceof EmailNotifier);
  }

  @Test
  void weShouldGetSmsNotifierWhenNotificationTypeIsSms() {

    //arrange
    NotifierFactory factory = NotifierFactory.createInstance();

    //act
    Notifier smsNotifier = factory.getNotifier(NotificationType.SMS);

    //assert
    assertTrue(smsNotifier instanceof SMSNotifier);
  }

}