package com.example.ivan.myapplication.model;

import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Jack on 16.7.2016..
 */

public class BaseModel extends ParseObject {
    public void spremi(SaveCallback saveCallback){
        ParseACL acl = new ParseACL();
        acl.setReadAccess(ParseUser.getCurrentUser(),true);
        acl.setReadAccess("FeDDkPgPTn",true);
        this.setACL(acl);
        this.saveInBackground(saveCallback);
    }
}
