package com.companyname.activityplugin.services;

import java.util.Map;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class ActivitiesServices {
    public static final String module = ActivitiesServices.class.getName();

    //we have create the create

    //find update find all
    public static Map<String, Object> createActivity(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator(); // entity object which we are going to use to interact with the database
        try {
            GenericValue activityValue = delegator.makeValue("Activity");
            // Auto generating next sequence of ofbizDemoId primary key
            activityValue.setNextSeqId();
            // Setting up all non primary key field values from context map
            activityValue.setNonPKFields(context);
            // Creating record in database for OfbizDemo entity for prepared value
            activityValue = delegator.create(activityValue);
            result.put("ActivityId", activityValue.getString("ActivityId"));
            Debug.log("==========This is my first Java Service implementation in Apache OFBiz. OfbizDemo record created successfully with ofbizDemoId: "+activityValue.getString("ActivityId"));
        } catch (GenericEntityException e) {
            Debug.logError(e, module);
            return ServiceUtil.returnError("Error in creating record in OfbizDemo entity ........" +module);
        }
        return result;
    }

    public static Map<String, Object> updateActivity(DispatchContext dctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        Delegator delegator = dctx.getDelegator();

        try {
//            GenericValue ofbizDemo = delegator.findByPrimaryKey(context.get("primary_key"));
            GenericValue updateEntity = delegator.makeValue("Activity");
            // Setting up all non primary key field values from context map
            updateEntity.setPKFields(context);
            // Creating record in database for OfbizDemo entity for prepared value
            updateEntity.setNonPKFields(context);
            delegator.store(updateEntity);

        } catch (GenericEntityException e) {
            Debug.logError(e, module);
            return ServiceUtil.returnError("Error in creating record in OfbizDemo entity ........" +module);
        }
        return result;
    }
}