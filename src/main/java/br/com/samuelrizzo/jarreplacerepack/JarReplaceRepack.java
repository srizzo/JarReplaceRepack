package br.com.samuelrizzo.jarreplacerepack;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

import de.schlichtherle.truezip.file.TFile;

public class JarReplaceRepack extends Application<IOException> {

    public static void main(String[] args) throws IOException {
        System.exit(new JarReplaceRepack().run(args));
    }

    @Override
    protected int work(String[] args) throws IOException {
    	
    	final File jarFile;
		try {
	    	CodeSource codeSource = JarReplaceRepack.class.getProtectionDomain().getCodeSource();
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		
		
//    	File workingDir = new File(System.getProperty("user.dir"));
    	File workingDir = new File("/tmp/test/");
    	
    	
    	TFile[] jarFiles = new TFile(workingDir).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.matches(".*(jar|war|ear|zip)$") && !name.equals(jarFile.getName());
			}
		});
    	
    	File[] profiles = workingDir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});
    	
    	for (TFile workingJar : jarFiles) {
        	for (File workingProfile : profiles) {

        		TFile originalJar = new TFile(workingJar);
                TFile destinationJar = new TFile(workingProfile, workingJar.getName());
                
                if(destinationJar.exists()){
                	destinationJar.rm_r();
                }

        		File[] templates = workingProfile.listFiles(new FileFilter() {
        			@Override
        			public boolean accept(File file) {
        				return file.isFile();
        			}
        		});

                originalJar.cp_rp(destinationJar);
        		
        		for (File template : templates) {
        			
        			List<TFile> destinationFiles = findDestinationFileFor(destinationJar, template);
        			
        			if(destinationFiles.isEmpty()){
        				System.out.println("No ocurrences of " + template.getName() + " found at " + destinationJar.getAbsolutePath());
        			}
        			
        			for (TFile destinationFile : destinationFiles) {
        				System.out.println(destinationFile.getPath() + " replaced with " + template.getAbsolutePath());
                        new TFile(template).cp_rp(destinationFile);
					}
				}
    		}
		}
        
        return 0;
    }
    
    private List<TFile> findDestinationFileFor(TFile jar, File template) {
    	List<TFile> foundDestinations = new ArrayList<TFile>();
    	List<TFile> jarContent = ls_r(jar);
    	
    	
    	for (TFile file : jarContent) {
			if(file.isFile() && file.getName().equals(template.getName())){
				foundDestinations.add(file);
			}
		}
    	return foundDestinations;
	}

	private List<TFile> ls_r(TFile file) {
    	
    	List<TFile> files = new ArrayList<TFile>();
    	
        if (!file.exists())
            throw new IllegalArgumentException(file + " (file or directory does not exist)");
        
        files.add(file);
        
        if (file.listFiles() != null) {
            TFile[] entries = file.listFiles();
            int l = entries.length - 1;
            if (0 <= l) {
                int i = 0;
                while (i < l)
                    files.addAll(ls_r(entries[i++]));
                files.addAll(ls_r(entries[i]));
            }
        }
        
        return files;
    }    
}
