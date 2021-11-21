package com.pfa1.API.service;


import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.FFMPEGLocator;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;
import ws.schild.jave.VideoSize;

class FFMPEGLocat extends FFMPEGLocator{
	public FFMPEGLocat() {
		
	}
	@Override
	protected String getFFMPEGExecutablePath() {
		return "/usr/bin/ffmpeg";//Utilisation d'un logiciel externe ffmpeg pour la convertion
	}
}
//La convertion du video
public class VideoConverter {
	public static File convert(File source) throws EncoderException {
		File target = new File(source.getPath().replaceFirst("[.][^.]+$", "")+".avi");
		VideoAttributes video = new VideoAttributes();
		video.setCodec("libx264");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("avi");
		video.setFrameRate(new Integer(25));//Frame rate a 25fps
		video.setSize(new VideoSize(640, 480));
		attrs.setVideoAttributes(video);
		FFMPEGLocat tes = new FFMPEGLocat() ;
		tes.createExecutor();
		Encoder encoder = new Encoder(tes);
		try {
			encoder.encode(new MultimediaObject(source,tes), target, attrs);
		} catch (IllegalArgumentException | EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		return target;
	}
}
