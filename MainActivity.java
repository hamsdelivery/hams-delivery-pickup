package com.hamsdelivery.app;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.net.Uri;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    LinearLayout root;
    int blue = Color.rgb(21,101,192), navy = Color.rgb(11,31,58);

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        showHome();
    }

    TextView title(String s, int size) {
        TextView t = new TextView(this); t.setText(s); t.setTextSize(size);
        t.setTextColor(Color.WHITE); t.setPadding(24,18,24,18); return t;
    }

    Button btn(String text) {
        Button b = new Button(this); b.setText(text); b.setTextSize(16);
        b.setAllCaps(false); return b;
    }

    void base() {
        root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(245,248,252));
        setContentView(root);
    }

    void showHome() {
        base();
        TextView head = title("HAM'S DELIVERY & PICKUP\nFast, Safe & Always Reliable", 24);
        head.setBackgroundColor(navy); root.addView(head);
        TextView info = new TextView(this);
        info.setText("📍 Ggaba Road, Kampala\n📞 0702610579\n🗓 Monday to Sunday — We Move!");
        info.setTextSize(17); info.setPadding(24,24,24,24); root.addView(info);

        Button book = btn("📦  Book a Delivery / Pickup");
        book.setOnClickListener(v -> showBooking()); root.addView(book);

        Button track = btn("🔄  Track My Order");
        track.setOnClickListener(v -> showTracking()); root.addView(track);

        Button call = btn("📞  Call HAM'S");
        call.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0702610579"))));
        root.addView(call);

        Button wa = btn("💬  WhatsApp HAM'S");
        wa.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/256702610579"));
            startActivity(i);
        }); root.addView(wa);

        TextView foot = new TextView(this);
        foot.setText("\n🚚 Your trusted delivery partner\n\nBook your pickup today!");
        foot.setTextSize(18); foot.setTextColor(blue); foot.setGravity(Gravity.CENTER);
        root.addView(foot);
    }

    EditText field(String hint) {
        EditText e = new EditText(this); e.setHint(hint); e.setTextSize(16);
        e.setPadding(20,10,20,10); root.addView(e);
        return e;
    }

    void showBooking() {
        base();
        root.addView(title("Book a Delivery / Pickup", 23));
        field("Customer name");
        field("Phone number");
        field("Pickup location");
        field("Delivery destination");
        field("Package / item description");
        field("Preferred time");
        Button submit = btn("🚚 Submit Booking");
        submit.setOnClickListener(v -> {
            new AlertDialog.Builder(this).setTitle("Booking Received")
                .setMessage("Thank you! HAM'S DELIVERY has received your request. We will contact you on 0702610579.")
                .setPositiveButton("OK", (d,w) -> showHome()).show();
        });
        root.addView(submit);
        Button back = btn("← Back"); back.setOnClickListener(v -> showHome()); root.addView(back);
    }

    void showTracking() {
        base();
        root.addView(title("Track My Order", 23));
        field("Enter order number");
        TextView status = new TextView(this);
        status.setText("\nOrder status:\n\n1. ✓ Request Received\n2. Rider Assigned\n3. Pickup\n4. On the Way\n5. Delivered");
        status.setTextSize(18); status.setPadding(24,20,24,20); root.addView(status);
        Button back = btn("← Back"); back.setOnClickListener(v -> showHome()); root.addView(back);
    }
}
