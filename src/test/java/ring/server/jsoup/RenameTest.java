package ring.server.jsoup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RenameTest {
	@Test
	public void rename(){
//		 //想命名的原文件的路径  
//        File file = new File("f:/a/a.xlsx");  
//        //将原文件更改为f:\a\b.xlsx，其中路径是必要的。注意  
//        file.renameTo(new File("f:/a/b.xlsx"));  
//        //想命名的原文件夹的路径  
//        File file1 = new File("f:/A");  
//        //将原文件夹更改为A，其中路径是必要的。注意  
//        file1.renameTo(new File("f:/B"));  
		
		List<File> list = new ArrayList<>();
		children(new File("C:\\Users\\ring\\Downloads\\ring-server-jsoup-master"), list);
		System.out.println(list.size());
		
	}
	
	private void children(File directoryFile,List<File> list){
		File[] files = directoryFile.listFiles();
		if(files!=null&&files.length>0){			
			for(File file:files ){
				if(file.isFile()&&file.getName().endsWith(".js")){
					//System.out.println(file.getPath());
					System.out.println(file.getParent()+File.separator+file.getName());
					file.renameTo(new File(file.getParent()+File.separator+file.getName()+".xx"));  
					list.add(file);
				}
				if(file.isDirectory()){
					children(file, list);
				}
			}
		}
	}
	
}
