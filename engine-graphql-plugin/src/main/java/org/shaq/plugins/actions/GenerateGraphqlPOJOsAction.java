package org.shaq.plugins.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.shaq.plugins.generation.GenerationInputManager;
import org.shaq.plugins.generation.GenerationProcessManager;
import org.shaq.plugins.generation.output.JaveComponentsPackageWriter;
import org.shaq.plugins.models.ProjectModel;
import org.shaq.plugins.models.graphql.GraphqlSchema;
import org.shaq.plugins.models.javafile.FileJavaComponent;
import org.shaq.plugins.utils.EnvironmentDataFetcher;
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
