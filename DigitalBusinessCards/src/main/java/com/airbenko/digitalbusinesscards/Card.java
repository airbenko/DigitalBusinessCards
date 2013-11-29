package com.airbenko.digitalbusinesscards;

import java.io.Serializable;

/**
 * Created by Joshua Benko on 11/29/13.
 * This class contains all of the data related to a person's business card'
 *
 * Implements serializable to allow saving to file
 */

public class Card implements Serializable {

    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String mailAddress;
    public String website;
    public String logoPath;
    public String imagePath;
    // Version ID of the serialized class
    private static final long serialVersionUID = 0L;

    /*
    Default constructor
     */
    public Card() {
        firstName = "";
        lastName = "";
        phoneNumber = "";
        mailAddress = "";
        website = "";
        logoPath = "";
        imagePath = "";

    }

}
