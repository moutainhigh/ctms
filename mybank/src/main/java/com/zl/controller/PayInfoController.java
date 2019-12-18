package com.zl.controller;

import com.zl.pojo.Paging;
import com.zl.pojo.PayInfo;
import com.zl.pojo.Query;
import com.zl.pojo.Transfer;
import com.zl.service.PayInfoService;
import com.zl.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息通知控制层
 *
 * @author 徐浩杰
 * @version 1.0 2019-12-12
 */
@Controller
@RequestMapping("payInfo")
public class PayInfoController {
    @Autowired
    private PayInfoService pis;
    @Autowired
    private TransferService ts;

    /**
     * 处理催款
     *
     * @param payInfo
     * @return
     */
    @RequestMapping("/dealPayee")
    public ModelAndView dealPayee(HttpSession session,PayInfo payInfo) {
        ModelAndView mv = new ModelAndView();

        Transfer transfer = new Transfer();
        transfer.setDealDate(new Date());
        transfer.setTransType(0);
        transfer.setAccOut((String) session.getAttribute("loginAccNo"));
        transfer.setAccIn(payInfo.getCreditorAcc());
        transfer.setTransFund(payInfo.getFund());
        transfer.setKind("收款转账");
        ts.transferMoney(transfer);

        System.out.println("执行成功");
        Map<String, Integer> map = new HashMap<>();
        map.put("status", 200);
        mv.addObject("map",map);
        mv.setViewName("transferAccountResult");
        return mv;
    }

    /**
     * 去催款通知前查询所有的催款通知，
     *
     * @return
     */
    @RequestMapping("/toPayInfoMessage")
    public ModelAndView toPayInfoMessage(Paging paging, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        paging.setRowsPage(10);//设置每页条数
        String loginAccNo = (String) session.getAttribute("loginAccNo");
        System.out.println("当前账户" + loginAccNo);

        Query query = new Query();
        query.setDebtor(loginAccNo);
        paging.setQuery(query);
        List<PayInfo> payInfos = pis.queryPayInfoByPaging(paging);
        System.out.println("消息通知" + payInfos);
        session.setAttribute("payInfos", payInfos);
        mv.setViewName("message01");
        return mv;
    }

    /**
     * 删除催款消息通知
     *
     * @param session
     * @param creditorAcc
     * @return
     */
    @RequestMapping("/deletePayInfo")
    public ModelAndView deletePayInfo(HttpSession session, String creditorAcc) {
        ModelAndView mv = new ModelAndView();
        System.out.println("进入删除payinfo");
        String loginAccNo = (String) session.getAttribute("loginAccNo");
        PayInfo payInfo = new PayInfo();
        payInfo.setCreditorAcc(creditorAcc);
        payInfo.setDebtor(loginAccNo);
        int flag = pis.deletePayInfo(payInfo);

        if (flag > 0) {
            System.out.println("删除成功！");
        } else {
            System.out.println("删除失败");
        }

        mv.setViewName("toPayInfoMessage");
        return mv;
    }

    /**
     * 去付款界面前传递transfer对象
     *
     * @return
     */
    @RequestMapping("/toMessage03")
    public ModelAndView toMessage03(HttpSession session, String creditorAcc) {
        ModelAndView mv = new ModelAndView();
        String loginAccNo = (String) session.getAttribute("loginAccNo");
        PayInfo pi = new PayInfo();
        pi.setCreditorAcc(creditorAcc);
        pi.setDebtor(loginAccNo);
        PayInfo payInfo = pis.queryPayInfo(pi);
        System.out.println("payInfo "+payInfo);
        mv.addObject("payInfo", payInfo);
        mv.setViewName("message03");
        return mv;
    }
}
