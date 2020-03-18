package iaf.ofek.skypath.plugins.utils;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import iaf.ofek.skypath.plugins.models.ProjectModel;

public class EnvironmentDataFetcher {

    public ProjectModel obtainProjectModel(AnActionEvent event) {
        final PsiDirectory directory = checkPath(event);
        final Project project = event.getProject();
        final VirtualFile virtualFolder = event.getData(LangDataKeys.VIRTUAL_FILE);
        final String packageName = ProjectRootManager
                .getInstance(project)
                .getFileIndex()
                .getPackageNameByDirectory(virtualFolder);
        return new ProjectModel.Builder()
                .setDirectory(directory)
                .setPackageName(packageName)
                .setProject(project)
                .setVirtualFolder(virtualFolder)
                .build();
    }

    public void refreshProject(ProjectModel projectModel) {
        ProjectView.getInstance(projectModel.getProject()).refresh();
        projectModel.getVirtualFolder().refresh(false, true);
    }

    private PsiDirectory checkPath(AnActionEvent event) {
        Object pathItem = event.getData(CommonDataKeys.NAVIGATABLE);
        if (pathItem != null) {
            if (pathItem instanceof PsiDirectory) {
                return (PsiDirectory) pathItem;
            }
        }
        throw new RuntimeException("Invalid Package Path!");
    }
}
