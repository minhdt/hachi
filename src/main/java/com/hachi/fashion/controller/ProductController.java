/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.controller;

import com.hachi.fashion.entity.Product;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.service.SB_ProductLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class ProductController extends BaseController<Product>{
    private List<UploadedFile> uploadFiles;
    
    @EJB
    private SB_ProductLocal service;
    
    @Override
    protected SB_BaseLocal<Product> getBaseService() {
        return service;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        System.out.println(event.getFile() != null);
        if (event.getFile() != null) {
            getUploadFiles().add(event.getFile());
            
            for (UploadedFile file : uploadFiles) {
                System.out.println(file.getFileName() + " - " + file.getContentType() + " - " + file.getSize());
            }
        }
    }

    public List<UploadedFile> getUploadFiles() {
        if (uploadFiles == null) {
            uploadFiles = new ArrayList<>();
        }
        return uploadFiles;
    }

    public void setUploadFiles(List<UploadedFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }
}
