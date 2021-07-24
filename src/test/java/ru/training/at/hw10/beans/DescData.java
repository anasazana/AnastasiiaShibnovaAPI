package ru.training.at.hw10.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class DescData {

    @SerializedName("emoji")
    @Expose
    private Emoji emoji;

    public Emoji getEmoji() {
        return emoji;
    }

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

}
