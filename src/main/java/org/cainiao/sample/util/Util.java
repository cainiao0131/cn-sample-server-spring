package org.cainiao.sample.util;

import lombok.experimental.UtilityClass;
import org.cainiao.sample.config.dao.DDLGenerator;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * <br />
 * <p>
 * Author: Cai Niao(wdhlzd@163.com)<br />
 */
@UtilityClass
public class Util {

    public static void subtraction(Set<Long> items1, Set<Long> items2) {
        Set<Long> smallerSet;
        Set<Long> biggerSet;
        if (items1.size() < items2.size()) {
            smallerSet = items1;
            biggerSet = items2;
        } else {
            smallerSet = items2;
            biggerSet = items1;
        }
        Iterator<Long> smallerIterator = smallerSet.iterator();
        while (smallerIterator.hasNext()) {
            Long smallerItem = smallerIterator.next();
            if (biggerSet.contains(smallerItem)) {
                smallerIterator.remove();
                biggerSet.remove(smallerItem);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DDLGenerator ddlGenerator = new DDLGenerator("org.cainiao.sample.entity", "src/main/resources/sql/ddl.sql");
        ddlGenerator.generateDDL();
    }
}
