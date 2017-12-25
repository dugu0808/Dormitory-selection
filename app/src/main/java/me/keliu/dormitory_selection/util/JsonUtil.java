package me.keliu.dormitory_selection.util;

import android.util.Log;

import org.json.JSONObject;

import me.keliu.dormitory_selection.bean.GetDetail;
import me.keliu.dormitory_selection.bean.GetRoom;
import me.keliu.dormitory_selection.bean.Login;
import me.keliu.dormitory_selection.bean.SelectRoom;

/**
 * Created by 45023 on 2017/12/25.
 */
public class JsonUtil {


    //解析登陆请求返回的json数据
    public static Login parseLoginJson(String responseData){
        Login loginResult = null;
        try{
            JSONObject jsonObject = new JSONObject(responseData);
            String errcode = jsonObject.getString("errcode");
            JSONObject jsonObject1= jsonObject.getJSONObject("data");
            String errMsg = jsonObject1.getString("errmsg");
            Log.d("err",errcode);
            Log.d("msg",errMsg);
            loginResult = new Login(errcode, errMsg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return loginResult;
    }

    public static GetDetail parseInformationJson(String responseData){
        GetDetail studentInformation = null;
        try{
            studentInformation = new GetDetail();
            JSONObject jsonObject = new JSONObject(responseData);

            String errCode = jsonObject.getString("errcode");
            studentInformation.setErrCode(errCode);

            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            studentInformation.setStuId(jsonObject1.getString("studentid"));
            studentInformation.setStuName(jsonObject1.getString("name"));
            studentInformation.setStuGender(jsonObject1.getString("gender"));
            studentInformation.setStuVcode(jsonObject1.getString("vcode"));
            if(jsonObject1.has("room")){
                studentInformation.setStuBuilding(jsonObject1.getString("room"));
            }
            if(jsonObject1.has("building")){
                studentInformation.setStuBuilding(jsonObject1.getString("building"));
            }
            studentInformation.setStuLocation(jsonObject1.getString("location"));
            studentInformation.setStuGrade(jsonObject1.getString("grade"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return studentInformation;
    }

    public static GetRoom parseDormitoryInformation(String responseData){
        GetRoom dormitoryInformation  = null;
        try{
            dormitoryInformation = new GetRoom();
            JSONObject jsonObject = new JSONObject(responseData);

            String errCode = jsonObject.getString("errcode");
            dormitoryInformation.setErrCode(errCode);

            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            dormitoryInformation.setFifthNumber(jsonObject1.getString("5"));
            dormitoryInformation.setThirteenthNumber(jsonObject1.getString("13"));
            dormitoryInformation.setFourteenthNumber(jsonObject1.getString("14"));
            dormitoryInformation.setEighthNumber(jsonObject1.getString("8"));
            dormitoryInformation.setNinethNumber(jsonObject1.getString("9"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return dormitoryInformation;
    }

    public static SelectRoom parseSelectionResult(String responseData){
        SelectRoom selectionResult = null;
        try{
            selectionResult = new SelectRoom();
            JSONObject jsonObject = new JSONObject(responseData);
            String errcode = jsonObject.getString("errcode");
            selectionResult.setErrcode(errcode);
        } catch (Exception e){
            e.printStackTrace();
        }
        return selectionResult;
    }
}

