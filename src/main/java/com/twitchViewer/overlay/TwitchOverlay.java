package com.twitchViewer.overlay;



import com.twitchViewer.ChatMessages;
import com.twitchViewer.TwitchViewerConfig;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;

public class TwitchOverlay extends Overlay {
    private final Client client;
    private final TwitchViewerConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    private final ChatMessages chats;



    @Inject
    private TwitchOverlay(Client client, TwitchViewerConfig config, ChatMessages chats){
        this.chats = chats;


        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        this.client = client;
        this.config = config;

    }

    @Override
    public Dimension render(Graphics2D graphics){


        ArrayList<String> chatMessages = new ArrayList<String>(1);
        chatMessages.add("TEST");




        panelComponent.getChildren().clear();
        String overlayTitle = "PlaceHolder Twitch Channel Text";

        panelComponent.getChildren().add(TitleComponent.builder()
        .text(overlayTitle)
        .color(Color.green)
        .build());

        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(overlayTitle) + 30,
        0));

        panelComponent.getChildren().add(LineComponent.builder()
        .left("PlaceholderViewer")
        .right("PlaceHolder2")
        .build());

        return panelComponent.render(graphics);
    }
}
