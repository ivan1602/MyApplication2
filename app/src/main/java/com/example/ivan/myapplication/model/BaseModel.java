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
        acl.setWriteAccess(ParseUser.getCurrentUser(),true);
        acl.setWriteAccess("FeDDkPgPTn",true);
        acl.setReadAccess(ParseUser.getCurrentUser(),true);
        acl.setReadAccess("FeDDkPgPTn",true);
        this.setACL(acl);
        if(saveCallback!=null)
            this.saveInBackground(saveCallback);
        else
            this.saveInBackground();
    }
}
