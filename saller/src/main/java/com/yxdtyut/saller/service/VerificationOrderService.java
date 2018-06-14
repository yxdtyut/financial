package com.yxdtyut.saller.service;

import com.yxdtyut.entity.VerificationOrder;
import com.yxdtyut.exception.GlobleException;
import com.yxdtyut.result.CodeMsg;
import com.yxdtyut.saller.enums.ChanEnum;
import com.yxdtyut.saller.repository.VerificationOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:04 2018/6/13
 */
@Service
@Slf4j
public class VerificationOrderService {
    @Autowired
    private VerificationOrderRepository verificationOrderRepository;

    @Value("${verification.rootDir:/Users/yangxudong/Desktop/picture}")
    private String rootDir;

    /** 对账文件内容换行符.*/
    private static String END_LINE = System.getProperty("line.seperator","\n");

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * @Author : yangxudong
     * @Description : 生成对账文件
     * @param chanId
     * @param date
     * @Date : 上午10:08 2018/6/13
     */
    public File makeVerificationFile(String chanId, Date date) {
        File verificationFile = getFile(rootDir,chanId,date);
        if (verificationFile.exists()) {
            return verificationFile;
        }
        try {
            verificationFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date startDate = createYMDDate(date);
        long entTime = startDate.getTime() + 1000*60*60*24;
        Date endDate = createYMDDate(new Date(entTime));
        List<String> verifyOrderStrs = verificationOrderRepository.queryVerificationOrders(chanId, startDate, endDate);
        log.info("对账信息:{}", verifyOrderStrs);
        String content = String.join(END_LINE, verifyOrderStrs);
        FileUtil.writeAsString(verificationFile, content);
        return verificationFile;
    }

    /**
     * @Author : yangxudong
     * @Description : 创建时间，格式为yyyy-MM-dd
     * @param date
     * @Date : 下午3:56 2018/6/13
     */
    private Date createYMDDate(Date date) {

        Date startDate = null;
        try {
            startDate = DATE_FORMAT.parse(DATE_FORMAT.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public void saveRemoteVerificationOrder(String chanId, Date date) {
        ChanEnum chanEnum = ChanEnum.getChanEnumById(chanId);
        if (null == chanEnum) {
            log.error(CodeMsg.CHAN_NOT_RIGHT.getMsg());
            throw new GlobleException(CodeMsg.CHAN_NOT_RIGHT);
        }
        //从ftp下载文件保存至本地(TODO)
        File file = getFile(chanEnum.getRootDir(),chanId, date);
        String content = null;
        try {
            content = FileUtil.readAsString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] props = content.split(END_LINE);
        List<VerificationOrder> verificationOrderList = new ArrayList<>();
        for (String line: props) {
            VerificationOrder order = parseLine(line);
            verificationOrderList.add(order);
        }
        verificationOrderRepository.save(verificationOrderList);
    }


    public File getFile(String rootDir, String chanId, Date date) {
        String dateStr = DATE_FORMAT.format(date);
        String name = chanId + "-" + dateStr + ".txt";
        File file = Paths.get(rootDir, name).toFile();
        return file;
    }

    /**
     * @Author : yangxudong
     * @Description : 解析对账文件每行数据生成对应的VerificationOrder对象
     *                顺序：order_id,outer_order_id,chan_id,chan_user_id,product_id,order_type,amount,create_at
     * @param line
     * @Date : 下午1:51 2018/6/13
     */
    public static VerificationOrder parseLine(String line) {
        VerificationOrder order = new VerificationOrder();
        String[] props = line.split("\\|");
        order.setOrderId(props[0]);
        order.setOuterOrderId(props[1]);
        order.setChanId(props[2]);
        order.setChanUserId(props[3]);
        order.setProductId(props[4]);
        order.setOrderType(props[5]);
        order.setAmount(new BigDecimal(props[6]));
        try {
            order.setCreateAt(DATETIME_FORMAT.parse(props[7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * @Author : yangxudong
     * @Description : 对账
     * @param chanId
     * @param date
     * @Date : 下午3:55 2018/6/13
     */
    public List<String> verifyOrder(String chanId,Date date) {
        List<String> errors = new ArrayList<>();
        Date startDate = createYMDDate(date);
        long entTime = startDate.getTime() + 1000*60*60*24;
        Date endDate = createYMDDate(new Date(entTime));
        List<String> excessOrder = verificationOrderRepository.queryExcessOrder(chanId, startDate, endDate);
        List<String> missOrder = verificationOrderRepository.queryMissOrder(chanId, startDate, endDate);
        List<String> diffOrder = verificationOrderRepository.queryDifferentOrder(chanId, startDate, endDate);
        errors.add("长款订单:" + String.join(",", excessOrder));
        errors.add("漏单订单:" + String.join(",", missOrder));
        errors.add("不一致订单:" + String.join(",", diffOrder));
        return errors;
    }
}
