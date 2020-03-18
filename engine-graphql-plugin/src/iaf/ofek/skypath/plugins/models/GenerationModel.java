package iaf.ofek.skypath.plugins.models;

public class GenerationModel {

    private String rootClassName;
    private String content;

    public String getRootClassName() {
        return rootClassName;
    }

    public String getContent() {
        return content;
    }

    public static class Builder {
        private GenerationModel instance;

        public Builder() {
            instance = new GenerationModel();
        }

        public Builder setRootClassName(String rootClassName) {
            instance.rootClassName = rootClassName;
            return this;
        }

        public Builder setContent(String content) {
            instance.content = content;
            return this;
        }

        public GenerationModel build() {
            return instance;
        }
    }

}
