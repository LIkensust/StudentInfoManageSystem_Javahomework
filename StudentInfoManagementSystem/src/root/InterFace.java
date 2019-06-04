package root;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InterFace {
    public void RootIntrtFace() {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("学生成绩管理系统");
        //Container root_pane = frame.getRootPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);

        //set menu
        MenuManage.SetMenu(frame);

        //set tab
        SetTabbed(frame);

        //frame.pack();
        //display the window
        frame.setVisible(true);
    }

    private void SetTabbed(JFrame frame) {
        //JScrollPane mainscroll = new JScrollPane();
        JTabbedPane tabpane = new JTabbedPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        tabpane.add("基础查询",p1);
        tabpane.add("成绩查询",p2);
        tabpane.add("信息管理",p3);

        //set page1
        PartlySet setp1 = new PartlySet();
        setp1.BasicSelectInterFace(p1);

        //mainscroll.add(tabpane);
        frame.add(tabpane);
    }
}

class MenuManage {
    public static void SetMenu(JFrame frame) {
        JMenuBar menubar= new JMenuBar();
        JMenu menu_file = new JMenu("file");
        JMenuItem menu_file_exit = new JMenuItem("exit");
        menu_file.add(menu_file_exit);
        menubar.add(menu_file);
        frame.setJMenuBar(menubar);
    }
}

class PartlySet implements ItemListener{
    JPanel cards;

    public void BasicSelectInterFace(Container conn) {
        //conn.setLayout(new BorderLayout());
        conn.setLayout(new BorderLayout());
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {"按学号查询",
                                  "分类查询",
                                  "显示全部"};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        JPanel card1 = new JPanel();
        PageSet_page1 setter1 = new PageSet_page1(card1);

        JPanel card2 = new JPanel();
        PageSet_page2 setter2 = new PageSet_page2(card2);

        JPanel card3 = new JPanel();
        PageSet_page3 setter3 = new PageSet_page3(card3);

        cards = new JPanel(new CardLayout());
        cards.add(card1,comboBoxItems[0]);
        cards.add(card2,comboBoxItems[1]);
        cards.add(card3,comboBoxItems[2]);

        conn.add(comboBoxPane,BorderLayout.PAGE_START);
        conn.add(cards,BorderLayout.CENTER);
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards,(String)itemEvent.getItem());
    }
}

//select by ID
class PageSet_page1 implements ActionListener {
    private Container page_;
    private JTextField IDEditor;
    private JButton Search;
    private DefaultTableModel table_model;
    private JTable Msgtable;
    private JScrollPane scrollPane;

    String[] head = {"学号","姓名","性别","学院","班级"};
    PageSet_page1(Container page) {
        page_ = page;
        page_.setLayout(new BorderLayout());
        //page_.setLayout(new BorderLayout());
        setpage();
    }

    public void setpage() {
        page_.setLayout(new BorderLayout());
        //input
        IDEditor = new JTextField(12);

        JPanel top = new JPanel();
        //ok
        Search = new JButton("查询");
        Search.addActionListener(this);

        top.add(IDEditor);
        top.add(Search);

        page_.add(top,BorderLayout.PAGE_START);

        JPanel center = new JPanel();

        table_model = new DefaultTableModel(null,head);
        Msgtable = new JTable(table_model);
        scrollPane = new JScrollPane(Msgtable);
        center.add(scrollPane);

        page_.add(center,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cmd = actionEvent.getActionCommand();
        if(cmd.equals("查询")) {
            String idnum = IDEditor.getText();
            boolean inputisok = CheckTool.CheckId(idnum);
            if(inputisok == false) {
                JOptionPane.showMessageDialog(new JDialog(),"学号应该是12位的数字","请重试",JOptionPane.WARNING_MESSAGE);
            } else {
                DatabaseOperation dbop  = new DatabaseOperation();
                ArrayList<BasicStuentInfoBlob> msg = dbop.GetStudentBasicInfo_byID(idnum);
                for(int i=0;i<table_model.getRowCount();i++) {
                    table_model.removeRow(i);
                }
                if(msg.size() == 0) {
                    JOptionPane.showMessageDialog(new JDialog(),"没有找到该学生相关信息","请重试",JOptionPane.WARNING_MESSAGE);
                } else {
                    for(int i=0;i<msg.size();i++) {
                        String[] oneline = new String[5];
                        oneline[0] = msg.get(i).getID_();
                        oneline[1] = msg.get(i).getName_();
                        oneline[2] = msg.get(i).getGender_();
                        oneline[3] = msg.get(i).getCollage_();
                        oneline[4] = msg.get(i).getClass_();
                        table_model.addRow(oneline);
                    }
                }
            }
            page_.revalidate();
        }
    }
}

//select by collage,class and name
class PageSet_page2 implements ActionListener {
    private Container page_;
    private JComboBox collageEditor;
    private JComboBox classEditor;
    private JTextField nameEditor;
    private JButton Search;
    private DefaultTableModel table_model;
    private JTable Msgtable;
    private JScrollPane scrollPane;

    String[] head = {"学号","姓名","性别","学院","班级"};
    PageSet_page2(Container page) {
        page_ = page;
        page_.setLayout(new BorderLayout());
        //page_.setLayout(new BorderLayout());
        setpage();
    }

    public void setpage() {
        page_.setLayout(new BorderLayout());
        //input
        DatabaseOperation dbop = new DatabaseOperation();
        collageEditor = new JComboBox();
        ArrayList<String> collegelist = dbop.GetCollegeList();
        for(int i=0; i < collegelist.size(); i++) {
            collageEditor.addItem(collegelist.get(i));
        }
        collageEditor.setEditable(true);

        classEditor = new JComboBox();
        ArrayList<String> classlist = dbop.GetClassList();
        for(int i = 0; i < classlist.size(); i++) {
            classEditor.addItem(classlist.get(i));
        }
        classEditor.setEditable(true);

        nameEditor = new JTextField(10);

        JPanel top = new JPanel();
        //ok
        Search = new JButton("查询");
        //Search.addKeyListener(this);
        Search.addActionListener(this);

        top.add(collageEditor);
        top.add(classEditor);
        top.add(nameEditor);
        top.add(Search);

        page_.add(top,BorderLayout.PAGE_START);

        JPanel center = new JPanel();

        table_model = new DefaultTableModel(null,head);
        Msgtable = new JTable(table_model);
        scrollPane = new JScrollPane(Msgtable);
        center.add(scrollPane);

        page_.add(center,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cmd = actionEvent.getActionCommand();
        if(cmd.equals("查询")) {
            String collegename = (String)collageEditor.getSelectedItem();
            String classname = (String)classEditor.getSelectedItem();
            String studentname = nameEditor.getText();
            DatabaseOperation dbop  = new DatabaseOperation();
            ArrayList<BasicStuentInfoBlob> msg = dbop.GetStudentBasicInfo_byCollegeClassAndName(collegename,classname,studentname);
            while(table_model.getRowCount()!=0) {
                table_model.removeRow(0);
            }
            if(msg.size() == 0) {
                JOptionPane.showMessageDialog(new JDialog(),"没有找到相关学生相关信息","请重试",JOptionPane.WARNING_MESSAGE);
            } else {
                for(int i=0;i<msg.size();i++) {
                    String[] oneline = new String[5];
                    oneline[0] = msg.get(i).getID_();
                    oneline[1] = msg.get(i).getName_();
                    oneline[2] = msg.get(i).getGender_();
                    oneline[3] = msg.get(i).getCollage_();
                    oneline[4] = msg.get(i).getClass_();
                    table_model.addRow(oneline);
                }
            }
            page_.revalidate();
        }
    }
}

//show all
class PageSet_page3 implements ActionListener {
    private Container page_;
    private JButton Search;
    private DefaultTableModel table_model;
    private JTable Msgtable;
    private JScrollPane scrollPane;

    String[] head = {"学号","姓名","性别","学院","班级"};
    PageSet_page3(Container page) {
        page_ = page;
        page_.setLayout(new BorderLayout());
        //page_.setLayout(new BorderLayout());
        setpage();
    }

    public void setpage() {
        page_.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        //ok
        Search = new JButton("查询");
        Search.addActionListener(this);

        top.add(Search);

        page_.add(top,BorderLayout.PAGE_START);

        JPanel center = new JPanel();

        table_model = new DefaultTableModel(null,head);
        Msgtable = new JTable(table_model);
        scrollPane = new JScrollPane(Msgtable);
        center.add(scrollPane);

        page_.add(center,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String cmd = actionEvent.getActionCommand();
        if(cmd.equals("查询")) {
            DatabaseOperation dbop  = new DatabaseOperation();
            ArrayList<BasicStuentInfoBlob> msg = dbop.GetStudentBasicInfo_all();
            while(table_model.getRowCount() !=0 ) {
                table_model.removeRow(0);
            }
            if(msg.size() == 0) {
                JOptionPane.showMessageDialog(new JDialog(),"没有找学生相关信息,数据未录入","请重试",JOptionPane.WARNING_MESSAGE);
            } else {
                for (int i = 0; i < msg.size(); i++) {
                    String[] oneline = new String[5];
                    oneline[0] = msg.get(i).getID_();
                    oneline[1] = msg.get(i).getName_();
                    oneline[2] = msg.get(i).getGender_();
                    oneline[3] = msg.get(i).getCollage_();
                    oneline[4] = msg.get(i).getClass_();
                    table_model.addRow(oneline);
                }
            }page_.revalidate();
        }
    }
}