package root;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        try{
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch (Exception e) {
            e.toString();
        }
        BeautyEyeLNFHelper.frameBorderStyle =BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
        UIManager.put("RootPane.setupButtonVisible", false);
        UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
        InterFace rootinterface = new InterFace();
        rootinterface.RootIntrtFace();
        //DatabaseOperation db = new DatabaseOperation();
        //db.createfakedata();
    }
}
