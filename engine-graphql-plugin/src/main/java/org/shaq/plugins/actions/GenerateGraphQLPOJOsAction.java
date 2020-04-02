package org.shaq.plugins.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.shaq.plugins.generation.GenerationInputManager;
import org.shaq.plugins.generation.GenerationProcessManager;
import org.shaq.plugins.generation.GraphQLGenerationContextAdapter;
import org.shaq.plugins.generation.output.JaveComponentsPackageWriter;
import org.shaq.plugins.models.logic.ProjectModel;
import org.shaq.plugins.models.javafile.FileJavaComponent;
import org.shaq.plugins.models.user.UserChoiceGraphQLContext;
import org.shaq.plugins.utils.EnvironmentDataFetcher;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GenerateGraphQLPOJOsAction extends AnAction {

    private EnvironmentDataFetcher dataFetcher;
    private GenerationInputManager inputManager;
    private GenerationProcessManager processManager;
    private JaveComponentsPackageWriter componentsWriter;

    public GenerateGraphQLPOJOsAction() {
        this.dataFetcher = new EnvironmentDataFetcher();
        this.inputManager = new GenerationInputManager(new GraphQLGenerationContextAdapter(), new SchemaParser());
        this.processManager = new GenerationProcessManager();
        this.componentsWriter = new JaveComponentsPackageWriter();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        ProjectModel projectModel = dataFetcher.obtainProjectModel(anActionEvent);
        UserChoiceGraphQLContext userChoices = inputManager.startGenerationInput();

        if (userChoices != null) {
            List<FileJavaComponent> javaComponents = processManager.startGeneration(userChoices, projectModel);
            componentsWriter.writeComponents(javaComponents, projectModel);
        }
    }
}
