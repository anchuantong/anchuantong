
package com.act.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import org.suigeneris.jrcs.diff.PatchFailedException;
import org.suigeneris.jrcs.rcs.Archive;
import org.suigeneris.jrcs.rcs.InvalidFileFormatException;
import org.suigeneris.jrcs.rcs.impl.NodeNotFoundException;
import org.suigeneris.jrcs.rcs.parse.ParseException;
import org.suigeneris.jrcs.util.ToString;

/**
 * @author an_chuantong
 */

public class ArchiveBean {

	public static String baseFile = "";
	public ArchiveBean archiveBean=new  ArchiveBean();
	public ArchiveBean() {
		URL is = getClass().getResource("/archive");
		if (is != null) {
			baseFile = is.getFile();
		}
	}

	public static Archive setArchive(String content) {

		ByteArrayInputStream bais = new ByteArrayInputStream(content.getBytes());
		try {
			return new Archive("", bais);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String getContent(String content) {
		Archive archive = ArchiveBean.setArchive(content);
		System.out.println("getRevisionVersion:" + archive.getRevisionVersion());
		try {
			return ToString.arrayToString(archive.getRevision());
		} catch (NodeNotFoundException e) {
			return null;
		} catch (InvalidFileFormatException e) {
			return null;
		} catch (PatchFailedException e) {
			return null;
		}
	}
}
