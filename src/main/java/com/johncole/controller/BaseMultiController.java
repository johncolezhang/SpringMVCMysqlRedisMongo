package com.johncole.controller;

import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by johncole on 2017/6/16.
 */
public class BaseMultiController {
    protected ModelAndView toView(final String url, Map<String, String> map) {
        ModelAndView view = new ModelAndView(url);
        view.addAllObjects(map);
        return view;
    }
}
