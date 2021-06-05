package com.twitchViewer.overlay;

import java.awt.*;
import javax.inject.Inject;

import com.twitchViewer.TwitchViewerConfig;
import net.runelite.api.Client;
import net.runelite.api.geometry.RectangleUnion;
import net.runelite.api.widgets.Widget;
import net.runelite.client.ui.overlay.*;
import com.twitchViewer.TwitchViewerPlugin;
import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class ViewerOverlay extends Overlay{

    @Override
    public Dimension render(Graphics2D graphics){

        return null;
    }

}
