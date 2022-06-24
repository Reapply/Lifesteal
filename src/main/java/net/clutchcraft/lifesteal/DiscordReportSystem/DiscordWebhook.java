package net.clutchcraft.lifesteal.DiscordReportSystem;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class DiscordWebhook {
    private final String url;
    private String content;
    private String username;
    private String avatarUrl;
    private boolean tts;
    private List<net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject> embeds = new ArrayList<>();

    /**
     * Constructs a new DiscordWebhook instance
     *
     * @param url The webhook URL obtained in Discord
     */
    public DiscordWebhook(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setTts(boolean tts) {
        this.tts = tts;
    }

    public void addEmbed(net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject embed) {
        this.embeds.add(embed);
    }

    public void execute() throws IOException {
        if (this.content == null && this.embeds.isEmpty()) {
            throw new IllegalArgumentException("Set content or add at least one EmbedObject");
        }

        net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject json = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

        json.put("content", this.content);
        json.put("username", this.username);
        json.put("avatar_url", this.avatarUrl);
        json.put("tts", this.tts);

        if (!this.embeds.isEmpty()) {
            List<net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject> embedObjects = new ArrayList<>();

            for (net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject embed : this.embeds) {
                net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject jsonEmbed = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

                jsonEmbed.put("title", embed.getTitle());
                jsonEmbed.put("description", embed.getDescription());
                jsonEmbed.put("url", embed.getUrl());

                TimeZone tz = TimeZone.getTimeZone("CEST");
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
                df.setTimeZone(tz);
                String nowAsISO = df.format(new Date());

                jsonEmbed.put("timestamp", nowAsISO);

                if (embed.getColor() != null) {
                    Color color = embed.getColor();
                    int rgb = color.getRed();
                    rgb = (rgb << 8) + color.getGreen();
                    rgb = (rgb << 8) + color.getBlue();

                    jsonEmbed.put("color", rgb);
                }

                net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Footer footer = embed.getFooter();
                net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Image image = embed.getImage();
                net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Thumbnail thumbnail = embed.getThumbnail();
                net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Author author = embed.getAuthor();
                List<net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Field> fields = embed.getFields();

                if (footer != null) {
                    net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject jsonFooter = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

                    jsonFooter.put("text", footer.getText());
                    jsonFooter.put("icon_url", footer.getIconUrl());
                    jsonEmbed.put("footer", jsonFooter);
                }

                if (image != null) {
                    net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject jsonImage = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

                    jsonImage.put("url", image.getUrl());
                    jsonEmbed.put("image", jsonImage);
                }

                if (thumbnail != null) {
                    net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject jsonThumbnail = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

                    jsonThumbnail.put("url", thumbnail.getUrl());
                    jsonEmbed.put("thumbnail", jsonThumbnail);
                }

                if (author != null) {
                    net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject jsonAuthor = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

                    jsonAuthor.put("name", author.getName());
                    jsonAuthor.put("url", author.getUrl());
                    jsonAuthor.put("icon_url", author.getIconUrl());
                    jsonEmbed.put("author", jsonAuthor);
                }

                List<net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject> jsonFields = new ArrayList<>();
                for (net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Field field : fields) {
                    net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject jsonField = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject();

                    jsonField.put("name", field.getName());
                    jsonField.put("value", field.getValue());
                    jsonField.put("inline", field.isInline());

                    jsonFields.add(jsonField);
                }

                jsonEmbed.put("fields", jsonFields.toArray());
                embedObjects.add(jsonEmbed);
            }

            json.put("embeds", embedObjects.toArray());
        }

        URL url = new URL(this.url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "Java-DiscordWebhook-BY-Gelox_");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream stream = connection.getOutputStream();
        stream.write(json.toString().getBytes());
        stream.flush();
        stream.close();

        connection.getInputStream().close(); //I'm not sure why but it doesn't work without getting the InputStream
        connection.disconnect();
    }

    public static class EmbedObject {
        private String title;
        private String description;
        private String url;
        private Color color;

        private net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Footer footer;
        private net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Thumbnail thumbnail;
        private net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Image image;
        private net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Author author;
        private List<net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Field> fields = new ArrayList<>();

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public Color getColor() {
            return color;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Footer getFooter() {
            return footer;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Thumbnail getThumbnail() {
            return thumbnail;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Image getImage() {
            return image;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Author getAuthor() {
            return author;
        }

        public List<net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Field> getFields() {
            return fields;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setTitle(String title) {
            this.title = title;
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setDescription(String description) {
            this.description = description;
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setUrl(String url) {
            this.url = url;
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setColor(Color color) {
            this.color = color;
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setFooter(String text, String icon) {
            this.footer = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Footer(text, icon);
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setThumbnail(String url) {
            this.thumbnail = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Thumbnail(url);
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setImage(String url) {
            this.image = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Image(url);
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject setAuthor(String name, String url, String icon) {
            this.author = new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Author(name, url, icon);
            return this;
        }

        public net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject addField(String name, String value, boolean inline) {
            this.fields.add(new net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.EmbedObject.Field(name, value, inline));
            return this;
        }

        private class Footer {
            private String text;
            private String iconUrl;

            private Footer(String text, String iconUrl) {
                this.text = text;
                this.iconUrl = iconUrl;
            }

            private String getText() {
                return text;
            }

            private String getIconUrl() {
                return iconUrl;
            }
        }

        private class Thumbnail {
            private String url;

            private Thumbnail(String url) {
                this.url = url;
            }

            private String getUrl() {
                return url;
            }
        }

        private class Image {
            private String url;

            private Image(String url) {
                this.url = url;
            }

            private String getUrl() {
                return url;
            }
        }

        private class Author {
            private String name;
            private String url;
            private String iconUrl;

            private Author(String name, String url, String iconUrl) {
                this.name = name;
                this.url = url;
                this.iconUrl = iconUrl;
            }

            private String getName() {
                return name;
            }

            private String getUrl() {
                return url;
            }

            private String getIconUrl() {
                return iconUrl;
            }
        }

        private class Field {
            private String name;
            private String value;
            private boolean inline;

            private Field(String name, String value, boolean inline) {
                this.name = name;
                this.value = value;
                this.inline = inline;
            }

            private String getName() {
                return name;
            }

            private String getValue() {
                return value;
            }

            private boolean isInline() {
                return inline;
            }
        }
    }

    private class JSONObject {

        private final HashMap<String, Object> map = new HashMap<>();

        void put(String key, Object value) {
            if (value != null) {
                map.put(key, value);
            }
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            builder.append("{");

            int i = 0;
            for (Map.Entry<String, Object> entry : entrySet) {
                Object val = entry.getValue();
                builder.append(quote(entry.getKey())).append(":");

                if (val instanceof String) {
                    builder.append(quote(String.valueOf(val)));
                } else if (val instanceof Integer) {
                    builder.append(Integer.valueOf(String.valueOf(val)));
                } else if (val instanceof Boolean) {
                    builder.append(val);
                } else if (val instanceof net.clutchcraft.lifesteal.DiscordReportSystem.DiscordWebhook.JSONObject) {
                    builder.append(val.toString());
                } else if (val.getClass().isArray()) {
                    builder.append("[");
                    int len = Array.getLength(val);
                    for (int j = 0; j < len; j++) {
                        builder.append(Array.get(val, j).toString()).append(j != len - 1 ? "," : "");
                    }
                    builder.append("]");
                }

                builder.append(++i == entrySet.size() ? "}" : ",");
            }

            return builder.toString();
        }

        private String quote(String string) {
            return "\"" + string + "\"";
        }
    }


}
