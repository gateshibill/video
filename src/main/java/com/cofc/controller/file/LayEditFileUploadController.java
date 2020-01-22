package com.cofc.controller.file;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cofc.util.BaseUtil;
import com.cofc.util.JsonUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
@RequestMapping("/layfileUpload")
public class LayEditFileUploadController extends BaseUtil{
	@RequestMapping(value = "/upload")
	public void upload(@RequestParam(value = "file")CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println("layfileUpload");
		String respJson = null;
		if (file == null) {
			respJson=JsonUtil.buildFalseJson("1", "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 10000000) { // 1M
			respJson=JsonUtil.buildFalseJson("2", "文件大小限制在1M以内!");
			output(response, respJson);
			return;
		}
		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String filehouzui = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
		String filename = format.format(now) + random + filehouzui;// 文件名
		String path = request.getServletContext().getRealPath("/") + "goodsImage" + "/" + filename;//文件所在盘路径
		String contextPath = request.getContextPath();//项目名
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/goodsImage/"
				+ filename;
		String minUrl = url;//缩略图覆盖原图
		System.out.println("minUrl:"+url);
		File oldFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(oldFile);
        
		//saveMinPhoto(path, path, 600, 400);
		//saveMinPhoto(path, path,1080,600);
		Map<String,Object> fileLay = new HashMap<String,Object>();
		fileLay.put("src", minUrl);
		fileLay.put("title", filename);
		respJson = JsonUtil.buildLayeditJson(fileLay);
		output(response, respJson);
	}

	// 等比例压缩
	public void saveMinPhoto(String srcURL, String deskURL, int Width, int Height) throws Exception {
		File srcFile = new File(srcURL);
		try {
			Image src = ImageIO.read(srcFile);
			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);
			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽
			double srcScale = (double) srcHeight / srcWidth;
			/** 缩略图宽高算法 */
			if (srcWidth > Width && srcHeight > Height) {
				if (srcWidth / Width > srcHeight / Height) {
					deskWidth = Width;
					deskHeight = srcHeight * deskWidth / srcWidth;
				} else {
					deskHeight = Height;
					deskWidth = srcWidth * deskHeight / srcHeight;
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}
			BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
			tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
			FileOutputStream deskImage = new FileOutputStream(deskURL); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
			encoder.encode(tag); // 近JPEG编码
			deskImage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
