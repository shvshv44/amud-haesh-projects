package iaf.ofek.skypath.plugins.generation.javafile.builder;

import iaf.ofek.skypath.plugins.models.clazz.AnnotationData;
import iaf.ofek.skypath.plugins.models.common.Keywords;
import iaf.ofek.skypath.plugins.models.common.ModifierType;
import iaf.ofek.skypath.plugins.models.common.Signs;
import iaf.ofek.skypath.plugins.models.enums.EnumFile;
import iaf.ofek.skypath.plugins.models.enums.EnumValue;
import iaf.ofek.skypath.plugins.utils.ImportCollector;
import iaf.ofek.skypath.plugins.utils.StringJavaFileBuilder;
import javafx.util.Pair;

import java.util.List;

public class EnumFileBuilder extends JavaComponentBuilder<EnumFile> {

    @Override
    protected String createFileData(StringJavaFileBuilder sb, EnumFile enumFile, ImportCollector importCollector) {
        sb.clear();
        sb.append(ModifierType.PUBLIC).space().append(Keywords.ENUM).space().append(enumFile.getName()).space().openBracket();
        sb.newLineIncTab();
        assignValues(sb,enumFile,importCollector);
        sb.newLineDecTab();
        sb.closeBracket();
        return sb.finish();
    }

    private void assignValues(StringJavaFileBuilder sb, EnumFile enumFile, ImportCollector importCollector) {

        List<EnumValue> values = enumFile.getValues();
        for(int index = 0; index < values.size(); index++) {

            EnumValue currValue = values.get(index);
            dealWithEnumValue(sb,currValue,importCollector);

            if(index + 1 < values.size()) {
                sb.append(Signs.NEXT_JAVA_VALUE);
            } else {
                sb.append(Signs.END_OF_JAVA_LINE);
            }
            sb.newLine();
        }

    }

    private void dealWithEnumValue(StringJavaFileBuilder sb, EnumValue value, ImportCollector importCollector) {
        dealWithAnnotation(sb,value,importCollector);
        sb.space().append(value.getName());
    }

    private void dealWithAnnotation(StringJavaFileBuilder sb, EnumValue value, ImportCollector importCollector) {
        AnnotationData annot = value.getAnnotationData();
        if(annot != null) {
            importCollector.collectImports(annot.getImports());
            sb.append("@").append(annot.getName()).space().openParamBracket();
            for(int index = 0; index < annot.getParameters().size(); index++) {
                Pair<String,String> parameter = annot.getParameters().get(index);
                sb.append(parameter.getKey()).equals().append(parameter.getValue());
                if(index + 1 < annot.getParameters().size()) {
                    sb.append(Signs.NEXT_JAVA_VALUE).space();
                }
            }
            sb.closeParamBracket();
            sb.newLine();
        }
    }

}
