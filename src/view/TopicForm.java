package view;

import javax.swing.*;

public class TopicForm extends JFrame
{
    private JPanel pnl_main;
    private JPanel pnl_message;
    private JPanel pnl_topicList;
    private JScrollPane scrl_pn_topicList;
    private JScrollPane scrl_pn_message;
    private JPanel pnl_topicButton;
    private JButton btn_newTopic;
    private JButton btn_joinTopic;
    private JButton btn_delete;
    private JButton btn_refresh;
    private JPanel pnl_utilsButton;
    private JTable tbl_topicList;

    public TopicForm()
    {
        initializeComponents();
        startListeners();
    }

    private void startListeners()
    {
    }

    private void initializeComponents()
    {

    }
}
