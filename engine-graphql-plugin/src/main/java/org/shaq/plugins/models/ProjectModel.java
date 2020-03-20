package org.shaq.plugins.models;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import lombok.Data;

@Data
public class ProjectModel {

    private PsiDirectory directory;
    private String packageName;
    private VirtualFile virtualFolder;
    private Project project;

    public static class Builder {
        private ProjectModel instance;

        public Builder() {
            instance = new ProjectModel();
        }

        public Builder setDirectory(PsiDirectory directory) {
            instance.directory = directory;
            return this;
        }

        public Builder setPackageName(String packageName) {
            instance.packageName = packageName;
            return this;
        }

        public Builder setVirtualFolder(VirtualFile virtualFolder) {
            instance.virtualFolder = virtualFolder;
            return this;
        }

        public Builder setProject(Project project) {
            instance.project = project;
            return this;
        }

        public ProjectModel build() {
            return instance;
        }
    }

}
