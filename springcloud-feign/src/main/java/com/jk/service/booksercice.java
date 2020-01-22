package com.jk.service;

import com.jk.model.HomestayMdel;
import com.jk.model.Praise;
import com.jk.model.UserMdel;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient("cloud-provider")
public interface booksercice extends Bookserver {


}
