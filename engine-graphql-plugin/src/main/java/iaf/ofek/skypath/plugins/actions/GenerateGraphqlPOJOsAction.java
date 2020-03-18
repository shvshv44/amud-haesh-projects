package iaf.ofek.skypath.plugins.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import iaf.ofek.skypath.plugins.generation.GenerationInputManager;
import iaf.ofek.skypath.plugins.generation.GenerationProcessManager;
import iaf.ofek.skypath.plugins.generation.output.JaveComponentsPackageWriter;
import iaf.ofek.skypath.plugins.models.ProjectModel;
import iaf.ofek.skypath.plugins.models.graphql.GraphqlSchema;
import iaf.ofek.skypath.plugins.models.javafile.FileJavaComponent;
import iaf.ofek.skypath.plugins.utils.EnvironmentDataFetcher;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GenerateGraphqlPOJOsAction extends AnAction {

    private EnvironmentDataFetcher dataFetcher;
    private GenerationInputManager inputManager;
    private GenerationProcessManager processManager;
    private JaveComponentsPackageWriter componentsWriter;

    public GenerateGraphqlPOJOsAction() {
        this.dataFetcher = new EnvironmentDataFetcher();
        this.inputManager = new GenerationInputManager();
        this.processManager = new GenerationProcessManager();
        this.componentsWriter = new JaveComponentsPackageWriter();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        ProjectModel projectModel = dataFetcher.obtainProjectModel(anActionEvent);
        GraphqlSchema schema = inputManager.startGenerationInput();
        List<FileJavaComponent> javaComponents = processManager.startGeneration(schema);
        componentsWriter.writeComponents(javaComponents, projectModel);
    }
}
