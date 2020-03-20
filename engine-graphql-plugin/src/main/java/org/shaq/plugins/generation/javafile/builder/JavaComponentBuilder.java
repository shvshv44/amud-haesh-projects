package org.shaq.plugins.generation.javafile.builder;

import org.shaq.plugins.models.javafile.FileJavaComponent;
import org.shaq.plugins.models.javafile.common.Keywords;
import org.shaq.plugins.models.javafile.common.Signs;
import org.shaq.plugins.utils.ImportCollector;
import org.shaq.plugins.utils.StringJavaFileBuilder;

public abstract class JavaComponentBuilder<T extends FileJavaComponent> {

    public String buildClassJavaContent(StringJavaFileBuilder sb, T component) {

        ImportCollector importCollector = new ImportCollector();
        String packageString = createPackage(sb,component);
        String dataString = createFileData(sb,component,importCollector);

        return buildFinalFileString(sb,packageString,dataString,importCollector);
    }

    private String createPackage(StringJavaFileBuilder sb, FileJavaComponent component) {
        sb.clear();
        sb.append(Keywords.PACKAGE).space().append(component.getPackagePath()).append(Signs.END_OF_JAVA_LINE);
        return sb.finish();
    }

    private String buildFinalFileString(StringJavaFileBuilder sb, String packageString, String dataString, ImportCollector importCollector) {
        sb.clear();
        sb.append(packageString).newLine().newLine();
        importCollector.assignImports(sb);
        sb.newLine();
        sb.append(dataString);
        return sb.finish();
    }

    protected abstract String createFileData(StringJavaFileBuilder sb, T component, ImportCollector importCollector);

}
