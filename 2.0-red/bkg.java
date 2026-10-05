import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class bkg extends axr {
   private static final Random b = new Random();
   private float c = 0.0F;
   private String d;
   private awg n;
   private int o = 0;
   private int p;
   private boolean q = true;
   private static boolean r = false;
   private static boolean s = false;
   private String t;
   private static final String[] u = new String[]{
      "/title/bg/panorama0.png",
      "/title/bg/panorama1.png",
      "/title/bg/panorama2.png",
      "/title/bg/panorama3.png",
      "/title/bg/panorama4.png",
      "/title/bg/panorama5.png"
   };
   public static final String clickMessage = "Please click " + a.t + "here" + a.v + " for more information.";
   private int v;
   private int w;
   private int x;
   private int y;
   private int z;
   private int A;

   public bkg() {
      this.d = "missingno";
      BufferedReader var1 = null;

      try {
         ArrayList var2 = new ArrayList();
         var1 = new BufferedReader(new InputStreamReader(bkg.class.getResourceAsStream("/title/splashes.txt"), Charset.forName("UTF-8")));

         String var3;
         while ((var3 = var1.readLine()) != null) {
            var3 = var3.trim();
            if (var3.length() > 0) {
               var2.add(var3);
            }
         }

         do {
            this.d = (String)var2.get(b.nextInt(var2.size()));
         } while (this.d.hashCode() == 125780783);
      } catch (IOException var12) {
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var11) {
            }
         }
      }

      this.c = b.nextFloat();
   }

   @Override
   public void c() {
      this.o++;
   }

   @Override
   public boolean f() {
      return false;
   }

   @Override
   protected void a(char var1, int var2) {
   }

   @Override
   public void A_() {
      this.p = this.f.p.a(new BufferedImage(256, 256, 2));
      Calendar var1 = Calendar.getInstance();
      var1.setTime(new Date());
      if (var1.get(2) + 1 == 11 && var1.get(5) == 9) {
         this.d = "Happy birthday, ez!";
      } else if (var1.get(2) + 1 == 6 && var1.get(5) == 1) {
         this.d = "Happy birthday, Notch!";
      } else if (var1.get(2) + 1 == 12 && var1.get(5) == 24) {
         this.d = "Merry X-mas!";
      } else if (var1.get(2) + 1 == 1 && var1.get(5) == 1) {
         this.d = "Happy new year!";
      } else if (var1.get(2) + 1 == 10 && var1.get(5) == 31) {
         this.d = "OOoooOOOoooo! Spooky!";
      }

      bp var2 = bp.a();
      byte var3 = 24;
      int var4 = this.h / 4 + 48;
      if (this.f.q()) {
         this.b(var4, 24, var2);
      } else {
         this.a(var4, 24, var2);
      }

      this.a(var2, var4, 24);
      if (this.f.n) {
         this.i.add(new awg(0, this.g / 2 - 100, var4 + 72, var2.a("menu.options")));
      } else {
         this.i.add(new awg(0, this.g / 2 - 100, var4 + 72 + 12, 98, 20, var2.a("menu.options")));
         this.i.add(new awg(4, this.g / 2 + 2, var4 + 72 + 12, 98, 20, var2.a("menu.quit")));
      }

      this.i.add(new axd(5, this.g / 2 - 124, var4 + 72 + 12));
      this.t = "";
      String var5 = System.getProperty("os_architecture");
      String var6 = System.getProperty("java_version");
      if ("ppc".equalsIgnoreCase(var5)) {
         this.t = "" + a.r + "Notice!" + a.v + " PowerPC compatibility will be dropped in Minecraft 1.6";
      } else if (var6 != null && var6.startsWith("1.5")) {
         this.t = "" + a.r + "Notice!" + a.v + " Java 1.5 compatibility will be dropped in Minecraft 1.6";
      }

      this.w = this.l.a(this.t);
      this.v = this.l.a(clickMessage);
      int var7 = Math.max(this.w, this.v);
      this.x = (this.g - var7) / 2;
      this.y = ((awg)this.i.get(0)).d - 24;
      this.z = this.x + var7;
      this.A = this.y + 24;
   }

   private void a(bp var1, int var2, int var3) {
      if (this.q) {
         if (!r) {
            r = true;
            new bkh(this, var1, var2, var3).start();
         } else if (s) {
            this.b(var1, var2, var3);
         }
      }
   }

   private void b(bp var1, int var2, int var3) {
      this.i.add(new awg(3, this.g / 2 - 100, var2 + var3 * 2, var1.a("menu.online")));
   }

   private void a(int var1, int var2, bp var3) {
      this.i.add(new awg(1, this.g / 2 - 100, var1, var3.a("menu.singleplayer")));
      this.i.add(new awg(2, this.g / 2 - 100, var1 + var2 * 1, var3.a("menu.multiplayer")));
   }

   private void b(int var1, int var2, bp var3) {
      this.i.add(new awg(11, this.g / 2 - 100, var1, var3.a("menu.playdemo")));
      this.i.add(this.n = new awg(12, this.g / 2 - 100, var1 + var2 * 1, var3.a("menu.resetdemo")));
      aki var4 = this.f.d();
      ajv var5 = var4.c("Demo_World");
      if (var5 == null) {
         this.n.g = false;
      }
   }

   @Override
   protected void a(awg var1) {
      if (var1.f == 0) {
         this.f.a(new axj(this, this.f.z));
      }

      if (var1.f == 5) {
         this.f.a(new axe(this, this.f.z));
      }

      if (var1.f == 1) {
         this.f.a(new axu(this));
      }

      if (var1.f == 2) {
         this.f.a(new axa(this));
      }

      if (var1.f == 3) {
         this.f.a(new bai(this));
      }

      if (var1.f == 4) {
         this.f.g();
      }

      if (var1.f == 11) {
         this.f.a("Demo_World", "Demo_World", iq.a);
      }

      if (var1.f == 12) {
         aki var2 = this.f.d();
         ajv var3 = var2.c("Demo_World");
         if (var3 != null) {
            awk var4 = axu.a(this, var3.k(), 12);
            this.f.a(var4);
         }
      }
   }

   @Override
   public void a(boolean var1, int var2) {
      if (var1 && var2 == 12) {
         aki var6 = this.f.d();
         var6.d();
         var6.e("Demo_World");
         this.f.a(this);
      } else if (var2 == 13) {
         if (var1) {
            try {
               Class var3 = Class.forName("java.awt.Desktop");
               Object var4 = var3.getMethod("getDesktop").invoke(null);
               var3.getMethod("browse", URI.class).invoke(var4, new URI("http://tinyurl.com/javappc"));
            } catch (Throwable var5) {
               var5.printStackTrace();
            }
         }

         this.f.a(this);
      }
   }

   private void b(int var1, int var2, float var3) {
      bge var4 = bge.a;
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GLU.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      GL11.glEnable(3042);
      GL11.glDisable(3008);
      GL11.glDisable(2884);
      GL11.glDepthMask(false);
      GL11.glBlendFunc(770, 771);
      byte var5 = 8;

      for (int var6 = 0; var6 < var5 * var5; var6++) {
         GL11.glPushMatrix();
         float var7 = ((float)(var6 % var5) / var5 - 0.5F) / 64.0F;
         float var8 = ((float)(var6 / var5) / var5 - 0.5F) / 64.0F;
         float var9 = 0.0F;
         GL11.glTranslatef(var7, var8, var9);
         GL11.glRotatef(kx.a((this.o + var3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-(this.o + var3) * 0.1F, 0.0F, 1.0F, 0.0F);

         for (int var10 = 0; var10 < 6; var10++) {
            GL11.glPushMatrix();
            if (var10 == 1) {
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var10 == 2) {
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var10 == 3) {
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var10 == 4) {
               GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (var10 == 5) {
               GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            this.f.p.b(u[var10]);
            var4.b();
            var4.a(16777215, 255 / (var6 + 1));
            float var11 = 0.0F;
            var4.a(-1.0, -1.0, 1.0, 0.0F + var11, 0.0F + var11);
            var4.a(1.0, -1.0, 1.0, 1.0F - var11, 0.0F + var11);
            var4.a(1.0, 1.0, 1.0, 1.0F - var11, 1.0F - var11);
            var4.a(-1.0, 1.0, 1.0, 0.0F + var11, 1.0F - var11);
            var4.a();
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
         GL11.glColorMask(true, true, true, false);
      }

      var4.b(0.0, 0.0, 0.0);
      GL11.glColorMask(true, true, true, true);
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GL11.glDepthMask(true);
      GL11.glEnable(2884);
      GL11.glEnable(3008);
      GL11.glEnable(2929);
   }

   private void a(float var1) {
      GL11.glBindTexture(3553, this.p);
      this.f.p.a();
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glColorMask(true, true, true, false);
      bge var2 = bge.a;
      var2.b();
      byte var3 = 3;

      for (int var4 = 0; var4 < var3; var4++) {
         var2.a(1.0F, 1.0F, 1.0F, 1.0F / (var4 + 1));
         int var5 = this.g;
         int var6 = this.h;
         float var7 = (var4 - var3 / 2) / 256.0F;
         var2.a(var5, var6, this.j, 0.0F + var7, 0.0);
         var2.a(var5, 0.0, this.j, 1.0F + var7, 0.0);
         var2.a(0.0, 0.0, this.j, 1.0F + var7, 1.0);
         var2.a(0.0, var6, this.j, 0.0F + var7, 1.0);
      }

      var2.a();
      GL11.glColorMask(true, true, true, true);
      this.f.p.a();
   }

   private void c(int var1, int var2, float var3) {
      GL11.glViewport(0, 0, 256, 256);
      this.b(var1, var2, var3);
      GL11.glDisable(3553);
      GL11.glEnable(3553);
      this.a(var3);
      this.a(var3);
      this.a(var3);
      this.a(var3);
      this.a(var3);
      this.a(var3);
      this.a(var3);
      this.a(var3);
      GL11.glViewport(0, 0, this.f.c, this.f.d);
      bge var4 = bge.a;
      var4.b();
      float var5 = this.g > this.h ? 120.0F / this.g : 120.0F / this.h;
      float var6 = this.h * var5 / 256.0F;
      float var7 = this.g * var5 / 256.0F;
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      var4.a(1.0F, 1.0F, 1.0F, 1.0F);
      int var8 = this.g;
      int var9 = this.h;
      var4.a(0.0, var9, this.j, 0.5F - var6, 0.5F + var7);
      var4.a(var8, var9, this.j, 0.5F - var6, 0.5F - var7);
      var4.a(var8, 0.0, this.j, 0.5F + var6, 0.5F - var7);
      var4.a(0.0, 0.0, this.j, 0.5F + var6, 0.5F + var7);
      var4.a();
   }

   @Override
   public void a(int var1, int var2, float var3) {
      this.c(var1, var2, var3);
      bge var4 = bge.a;
      short var5 = 274;
      int var6 = this.g / 2 - var5 / 2;
      byte var7 = 30;
      this.a(0, 0, this.g, this.h, -2130706433, 16777215);
      this.a(0, 0, this.g, this.h, 0, Integer.MIN_VALUE);
      this.f.p.b("/title/mclogo.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.c < 1.0E-4) {
         this.b(var6 + 0, var7 + 0, 0, 0, 99, 44);
         this.b(var6 + 99, var7 + 0, 129, 0, 27, 44);
         this.b(var6 + 99 + 26, var7 + 0, 126, 0, 3, 44);
         this.b(var6 + 99 + 26 + 3, var7 + 0, 99, 0, 26, 44);
         this.b(var6 + 155, var7 + 0, 0, 45, 155, 44);
      } else {
         this.b(var6 + 0, var7 + 0, 0, 0, 155, 44);
         this.b(var6 + 155, var7 + 0, 0, 45, 155, 44);
      }

      var4.d(16777215);
      GL11.glPushMatrix();
      GL11.glTranslatef(this.g / 2 + 90, 70.0F, 0.0F);
      GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
      float var8 = 1.8F - kx.e(kx.a((float)(Minecraft.G() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
      boolean var9 = System.currentTimeMillis() > 1364860800000L;
      var8 = var8 * 100.0F / (this.l.a(this.d) + 32);
      GL11.glScalef(var8, var8, var8);
      if (!var9) {
         this.a(this.l, this.d, 0, -8, 16776960);
      }

      if (var9) {
         GL11.glScalef(2.0F, 2.0F, 2.0F);
         GL11.glTranslatef(-25.0F, 10.0F, 5.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
         bp var10 = bp.a();
         this.a(this.l, var10.a("menu.aprilFools"), 0, -8, 16711935);
      }

      GL11.glPopMatrix();
      String var13 = "Minecraft 2.0";
      if (this.f.q()) {
         var13 = var13 + " Demo";
      }

      this.b(this.l, var13, 2, this.h - 10, 16777215);
      String var11 = "Copyright Mojang AB. Do not distribute!";
      this.b(this.l, var11, this.g - this.l.a(var11) - 2, this.h - 10, 16777215);
      if (this.t != null && this.t.length() > 0) {
         a(this.x - 2, this.y - 2, this.z + 2, this.A - 1, 1428160512);
         this.b(this.l, this.t, this.x, this.y, 16777215);
         this.b(this.l, clickMessage, (this.g - this.v) / 2, ((awg)this.i.get(0)).d - 12, 16777215);
      }

      super.a(var1, var2, var3);
   }

   @Override
   protected void a(int var1, int var2, int var3) {
      super.a(var1, var2, var3);
      if (this.t.length() > 0 && var1 >= this.x && var1 <= this.z && var2 >= this.y && var2 <= this.A) {
         bdn var4 = new bdn(this, "http://tinyurl.com/javappc", 13);
         var4.h();
         this.f.a(var4);
      }
   }
}
