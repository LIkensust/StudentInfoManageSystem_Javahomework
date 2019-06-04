package root;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SettingReader {
    private String setting_file_name_ = null;
    private boolean is_opened_ = false;
    private BufferedReader buffered_reader_ = null;
    private JSONObject Json_Object_ = null;
    public SettingReader(String file_name) {
        setting_file_name_ = file_name;
    }

    private int init() {
        try {
            buffered_reader_ = new BufferedReader(new FileReader(setting_file_name_));
            String line = buffered_reader_.readLine();
            ArrayList<String> tmp_list = new ArrayList<>();
            String file_text = new String();
            while(line != null) {
                tmp_list.add(line);
                line = buffered_reader_.readLine();
            }
            for (int i = 0;i < tmp_list.size();i++) {
                file_text = file_text + tmp_list.get(i);
            }
            Json_Object_ = JSONObject.parseObject(file_text);
            is_opened_ = true;
            buffered_reader_.close();
            return 0;
        } catch (IOException e) {
            is_opened_ = false;
            return -1;
        }
    }

    String GetString(String setting_name) {
        if(is_opened_ == false) {
            init();
        }
        return Json_Object_.getString(setting_name);
    }

    Integer GetInt(String setting_name) {
        if(is_opened_ == false) {
            init();
        }
        return Json_Object_.getInteger(setting_name);
    }

    Boolean GetBooleanValue(String setting_name) {
        if(is_opened_ == false) {
            init();
        }
        return Json_Object_.getBoolean(setting_name);
    }
}
