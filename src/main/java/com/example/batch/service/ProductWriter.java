package com.example.batch.service;

import com.example.batch.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {
    private ObjectMapper objectMapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Log logger = LogFactory.getLog(ProductWriter.class);

    private static final String INSERT_PRODUCT = "INSERT INTO `products` (main_cat, title, asin, category, imageURLHighRes, price) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("beforeStep " + stepExecution);
        objectMapper = new ObjectMapper();
        jdbcTemplate.setDataSource(dataSource);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("afterStep " + stepExecution);
        //connection.close();
        return null;
    }

    @Override
    public void write(List<? extends Product> list) throws Exception {
//        list.stream().forEach(System.out::println);
//        System.out.println("chunk written");
//        if (objectMapper == null) logger.warn("objectMapper == null");
//        int a = 0, b = 0;
        jdbcTemplate.batchUpdate(INSERT_PRODUCT,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        if (!list.get(i).getPrice().startsWith("$")) list.get(i).setPrice("");
                        try {
                            ps.setString(1, list.get(i).getMain_cat());
                            ps.setString(2, list.get(i).getTitle());
                            ps.setString(3, list.get(i).getAsin());
                            ps.setString(4, objectMapper.writeValueAsString(list.get(i).getCategory()));
                            ps.setString(5, objectMapper.writeValueAsString(list.get(i).getImageURLHighRes()));
                            ps.setString(6, list.get(i).getPrice());
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                });
//        synchronized (this) {
//            for (Product p : list) {
//                jdbcTemplate.update(INSERT_PRODUCT,
//                        p.getMain_cat(),
//                        p.getTitle(),
//                        p.getAsin(),
//                        objectMapper.writeValueAsString(p.getCategory()),
//                        objectMapper.writeValueAsString(p.getImageURLHighRes()),
//                        p.getPrice().startsWith("$") ? p.getPrice() : "");
//            }
//        }
        //System.out.println(list.size() + " " + a + " " + b);
    }
}
