package com.jk.service;

import com.jk.model.HomestayMdel;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("cloud-provider")
public interface booksercice extends Bookserver {


}
