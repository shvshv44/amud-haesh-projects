package org.shaq.plugins.generation.output;

import com.intellij.openapi.ui.Messages;
import org.shaq.plugins.generation.javafile.builder.JavaComponentBuilder;
import org.shaq.plugins.models.javafile.FileJavaComponent;
import org.shaq.plugins.models.logic.ProjectModel;
import org.shaq.plugins.utils.StringJavaFileBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class JaveComponentsPackageWriter {

    private StringJavaFileBuilder sb;
    private final String JAVA_FILE_POSTFIX = ".java";

    public JaveComponentsPackageWriter() {
        this.sb = new StringJavaFileBuilder();
    }

    public void writeComponents(List<FileJavaComponent> components, final ProjectModel projectModel) {

        for(FileJavaComponent component : components) {
            JavaComponentBuilder builder = component.getBuilder();
            String componentContent = builder.buildClassJavaContent(sb, component);
            final String path = projectModel.getDirectory().getVirtualFile().getPath();
            final String fileName = component.getName() + JAVA_FILE_POSTFIX;
            File file = new File(path + File.separator + fileName);

            try {

                if(file.exists()) {
                    file.delete();
                }

                FileUtils.writeStringToFile(file, componentContent);
            } catch (Exception e) {
                Messages.showDialog("Cannot generate class " + component.getName() + " , message: " + e.getMessage(),"Exception", new String[]{"OK"}, -1, null);
            }

            sb.clear();
        }

    }

}
