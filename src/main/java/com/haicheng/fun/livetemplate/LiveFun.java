package com.haicheng.fun.livetemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author linhaicheng on 2019/04/13
 */
public class LiveFun {

    public static void main(String[] args) {

    }

    //psfs
    //public static final String

    //psfi
    //public static final int

    //psf
    //private static final

    //geti
    //public static LiveFun getInstance() {
    //    return ;
    //}

    //toar thr st lazy itar inn ifn
    private static void fun() {

        //toar
        List<String> strings = Arrays.asList("1", "1");
        strings.toArray(new String[strings.size()]);

        //thr
        //throw new;

        //st
        //String

        // serr
        System.err.println();
        //souf
        System.out.printf("");
        //sout
        System.out.println();
        //soutm
        System.out.println("LiveFun.fun");
        //soup
        System.out.println();
        //soutv
        System.out.println("true = " + true);

        //ritar
        //for (int i = array.length - 1; i >= 0; i--) {
        //     = array[i];
        //
        //}

        //lst lash element of an array
        //[//.length - 1]

        //lazy
        String someting = "";
        if (someting == null) {
            someting = (String) new Object();
        }

        //itve
        //for (int i = 0; i < vector.size(); i++) {
        //    Object elementAt = vector.elementAt(i);
        //
        //}

        //ittok
        //for (StringTokenizer stringTokenizer = new StringTokenizer(); stringTokenizer.hasMoreTokens(); ) {
        //    String s = stringTokenizer.nextToken();
        //
        //}

        //itli
        //for (int i = 0; i < list.size(); i++) {
        //    Object o = list.get(i);
        //
        //}

        //itit
        //while (iterator.hasNext()) {
        //    Object next =  iterator.next();
        //
        //}

        //iten
        //while (enumeration.hasMoreElements()) {
        //    Object nextElement =  enumeration.nextElement();
        //
        //}

        //itco
        List<String> list = new ArrayList<>();
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();

        }

        //itar
        String[] array = new String[]{};
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
        }

        //inst
        //if (true instanceof Object) {
        //    Object o = (Object) true;
        //
        //}

        //inn
        if ("" != null) {

        }

        //ijws
        //MyService service = new PortLocator().getPort();
        ////invoke business method
        //service.businessMethod();

        //ijiws
        //try {
        //    MyLocator locator = new MyLocator();
        //    Activator service = locator.();
        //    // invoke business method
        //    service.();
        //} catch (java.rmi.RemoteException ex) {
        //    ex.printStackTrace();
        //}

        //ifn
        if ("" == null) {

        }

        // cxf
        //MyServiceName service = org.apache.cxf.jaxrs.client.JAXRSClientFactory.create("http://localhost:8080/cxfrestsample", MyServiceName.class);
        //String out = service.getClichedMessage();
        //System.out.println("service response was: " + out);

        // C
        //Callable<Object> callable = new Callable<Object>() {
        //    public Object call() throws Exception {
        //
        //    }
        //};
    }

    /**
     * iter
     *
     * @param strs
     */
    private static void fun2(String[] strs) {
        for (String str : strs) {
            System.out.println(str);
        }

    }

    /**
     * fori I
     */
    private static void fun1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

}
