import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.Bidi;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class awv {
   private int[] c = new int[256];
   public int a = 9;
   public Random b = new Random();
   private byte[] d = new byte[65536];
   private int[] e = new int[32];
   private final String f;
   private final bgf g;
   private float h;
   private float i;
   private boolean j;
   private boolean k;
   private float l;
   private float m;
   private float n;
   private float o;
   private int p;
   private boolean q = false;
   private boolean r = false;
   private boolean s = false;
   private boolean t = false;
   private boolean u = false;

   awv() {
      this.g = null;
      this.f = null;
   }

   public awv(avy var1, String var2, bgf var3, boolean var4) {
      this.f = var2;
      this.g = var3;
      this.j = var4;
      this.a();
      var3.b(var2);

      for (int var5 = 0; var5 < 32; var5++) {
         int var6 = (var5 >> 3 & 1) * 85;
         int var7 = (var5 >> 2 & 1) * 170 + var6;
         int var8 = (var5 >> 1 & 1) * 170 + var6;
         int var9 = (var5 >> 0 & 1) * 170 + var6;
         if (var5 == 6) {
            var7 += 85;
         }

         if (var1.g) {
            int var10 = (var7 * 30 + var8 * 59 + var9 * 11) / 100;
            int var11 = (var7 * 30 + var8 * 70) / 100;
            int var12 = (var7 * 30 + var9 * 70) / 100;
            var7 = var10;
            var8 = var11;
            var9 = var12;
         }

         if (var5 >= 16) {
            var7 /= 4;
            var8 /= 4;
            var9 /= 4;
         }

         this.e[var5] = (var7 & 0xFF) << 16 | (var8 & 0xFF) << 8 | var9 & 0xFF;
      }
   }

   public void a() {
      this.d();
      this.c(this.f);
   }

   private void c(String var1) {
      InputStream var2 = null;

      try {
         var2 = bgf.class.getResourceAsStream(var1);
         if (var2 == null) {
            throw new RuntimeException("Unable to load font: " + var1);
         }

         BufferedImage var3 = ImageIO.read(var2);
         if (var3 == null) {
            throw new RuntimeException("Unable to decode font: " + var1);
         }

         int var4 = var3.getWidth();
         int var5 = var3.getHeight();
         int[] var6 = new int[var4 * var5];
         var3.getRGB(0, 0, var4, var5, var6, 0, var4);
         int var7 = var4 / 16;
         int var8 = var5 / 16;
         if (var7 <= 0) {
            var7 = 8;
         }

         if (var8 <= 0) {
            var8 = 8;
         }

         for (int var9 = 0; var9 < 256; var9++) {
            int var10 = var9 % 16;
            int var11 = var9 / 16;

            int var12;
            for (var12 = var7 - 1; var12 >= 0; var12--) {
               int var13 = var10 * var7 + var12;
               boolean var14 = true;

               for (int var15 = 0; var15 < var8 && var14; var15++) {
                  int var16 = (var11 * var8 + var15) * var4;
                  if (var13 + var16 >= 0 && var13 + var16 < var6.length && (var6[var13 + var16] & 0xFF) > 0) {
                     var14 = false;
                  }
               }

               if (!var14) {
                  break;
               }
            }

            if (var9 == 32) {
               var12 = 2;
            }

            if (var7 != 8) {
               var12 = (int)((var12 + 1) * 8.0F / var7) - 1;
            }

            this.c[var9] = var12 + 2;
         }
      } catch (IOException var24) {
         throw new RuntimeException(var24);
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var23) {
            }
         }
      }
   }

   private void d() {
      InputStream var1 = null;

      try {
         var1 = Minecraft.x().D.e().a("/font/glyph_sizes.bin");
         if (var1 == null) {
            throw new RuntimeException("Unable to load /font/glyph_sizes.bin");
         }

         int var2 = 0;

         while (var2 < this.d.length) {
            int var3 = var1.read(this.d, var2, this.d.length - var2);
            if (var3 < 0) {
               break;
            }

            if (var3 != 0) {
               var2 += var3;
            }
         }
      } catch (IOException var11) {
         throw new RuntimeException(var11);
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var10) {
            }
         }
      }
   }

   private float a(int var1, char var2, boolean var3) {
      if (var2 == ' ') {
         return 4.0F;
      } else {
         return var1 > 0 && !this.j ? this.a(var1 + 32, var3) : this.a(var2, var3);
      }
   }

   private float a(int var1, boolean var2) {
      float var3 = var1 % 16 * 8;
      float var4 = var1 / 16 * 8;
      float var5 = var2 ? 1.0F : 0.0F;
      this.g.b(this.f);
      float var6 = this.c[var1] - 0.01F;
      GL11.glBegin(5);
      GL11.glTexCoord2f(var3 / 128.0F, var4 / 128.0F);
      GL11.glVertex3f(this.h + var5, this.i, 0.0F);
      GL11.glTexCoord2f(var3 / 128.0F, (var4 + 7.99F) / 128.0F);
      GL11.glVertex3f(this.h - var5, this.i + 7.99F, 0.0F);
      GL11.glTexCoord2f((var3 + var6) / 128.0F, var4 / 128.0F);
      GL11.glVertex3f(this.h + var6 + var5, this.i, 0.0F);
      GL11.glTexCoord2f((var3 + var6) / 128.0F, (var4 + 7.99F) / 128.0F);
      GL11.glVertex3f(this.h + var6 - var5, this.i + 7.99F, 0.0F);
      GL11.glEnd();
      return this.c[var1];
   }

   private void a(int var1) {
      String var2 = String.format("/font/glyph_%02X.png", var1);
      this.g.b(var2);
   }

   private float a(char var1, boolean var2) {
      if (this.d[var1] == 0) {
         return 0.0F;
      }

      int var3 = var1 / 256;
      this.a(var3);
      int var4 = this.d[var1] >>> 4;
      int var5 = this.d[var1] & 15;
      float var6 = var4;
      float var7 = var5 + 1.0F;
      float var8 = var1 % 16 * 16 + var6;
      float var9 = (var1 & 255) / 16 * 16;
      float var10 = var7 - var6 - 0.02F;
      float var11 = var2 ? 1.0F : 0.0F;
      GL11.glBegin(5);
      GL11.glTexCoord2f(var8 / 256.0F, var9 / 256.0F);
      GL11.glVertex3f(this.h + var11, this.i, 0.0F);
      GL11.glTexCoord2f(var8 / 256.0F, (var9 + 15.98F) / 256.0F);
      GL11.glVertex3f(this.h - var11, this.i + 7.99F, 0.0F);
      GL11.glTexCoord2f((var8 + var10) / 256.0F, var9 / 256.0F);
      GL11.glVertex3f(this.h + var10 / 2.0F + var11, this.i, 0.0F);
      GL11.glTexCoord2f((var8 + var10) / 256.0F, (var9 + 15.98F) / 256.0F);
      GL11.glVertex3f(this.h + var10 / 2.0F - var11, this.i + 7.99F, 0.0F);
      GL11.glEnd();
      return (var7 - var6) / 2.0F + 1.0F;
   }

   public int a(String var1, int var2, int var3, int var4) {
      return this.a(var1, var2, var3, var4, true);
   }

   public int b(String var1, int var2, int var3, int var4) {
      return this.a(var1, var2, var3, var4, false);
   }

   public int a(String var1, int var2, int var3, int var4, boolean var5) {
      this.e();
      if (this.k) {
         var1 = this.d(var1);
      }

      int var7;
      if (var5) {
         var7 = this.b(var1, var2 + 1, var3 + 1, var4, true);
         var7 = Math.max(var7, this.b(var1, var2, var3, var4, false));
      } else {
         var7 = this.b(var1, var2, var3, var4, false);
      }

      return var7;
   }

   private String d(String var1) {
      if (var1 != null && Bidi.requiresBidi(var1.toCharArray(), 0, var1.length())) {
         Bidi var2 = new Bidi(var1, -2);
         byte[] var3 = new byte[var2.getRunCount()];
         String[] var4 = new String[var3.length];

         for (int var5 = 0; var5 < var3.length; var5++) {
            int var6 = var2.getRunStart(var5);
            int var7 = var2.getRunLimit(var5);
            int var8 = var2.getRunLevel(var5);
            var4[var5] = var1.substring(var6, var7);
            var3[var5] = (byte)var8;
         }

         String[] var11 = (String[])var4.clone();
         Bidi.reorderVisually(var3, 0, var4, 0, var3.length);
         StringBuilder var12 = new StringBuilder();

         for (int var13 = 0; var13 < var4.length; var13++) {
            byte var14 = var3[var13];

            for (int var9 = 0; var9 < var11.length; var9++) {
               if (var11[var9].equals(var4[var13])) {
                  var14 = var3[var9];
                  break;
               }
            }

            if ((var14 & 1) == 0) {
               var12.append(var4[var13]);
            } else {
               for (int var15 = var4[var13].length() - 1; var15 >= 0; var15--) {
                  char var10 = var4[var13].charAt(var15);
                  if (var10 == '(') {
                     var10 = ')';
                  } else if (var10 == ')') {
                     var10 = '(';
                  }

                  var12.append(var10);
               }
            }
         }

         return var12.toString();
      } else {
         return var1;
      }
   }

   private void e() {
      this.q = false;
      this.r = false;
      this.s = false;
      this.t = false;
      this.u = false;
   }

   private void a(String var1, boolean var2) {
      for (int var3 = 0; var3 < var1.length(); var3++) {
         char var4 = var1.charAt(var3);
         if (var4 == 167 && var3 + 1 < var1.length()) {
            int var11 = "0123456789abcdefklmnor".indexOf(var1.toLowerCase().charAt(var3 + 1));
            if (var11 < 16) {
               this.q = false;
               this.r = false;
               this.u = false;
               this.t = false;
               this.s = false;
               if (var11 < 0 || var11 > 15) {
                  var11 = 15;
               }

               if (var2) {
                  var11 += 16;
               }

               int var13 = this.e[var11];
               this.p = var13;
               GL11.glColor4f((var13 >> 16) / 255.0F, (var13 >> 8 & 0xFF) / 255.0F, (var13 & 0xFF) / 255.0F, this.o);
            } else if (var11 == 16) {
               this.q = true;
            } else if (var11 == 17) {
               this.r = true;
            } else if (var11 == 18) {
               this.u = true;
            } else if (var11 == 19) {
               this.t = true;
            } else if (var11 == 20) {
               this.s = true;
            } else if (var11 == 21) {
               this.q = false;
               this.r = false;
               this.u = false;
               this.t = false;
               this.s = false;
               GL11.glColor4f(this.l, this.m, this.n, this.o);
            }

            var3++;
         } else {
            int var5 = v.a.indexOf(var4);
            if (this.q && var5 > 0) {
               int var6;
               do {
                  var6 = this.b.nextInt(v.a.length());
               } while (this.c[var5 + 32] != this.c[var6 + 32]);

               var5 = var6;
            }

            float var12 = this.j ? 0.5F : 1.0F;
            boolean var7 = (var5 <= 0 || this.j) && var2;
            if (var7) {
               this.h -= var12;
               this.i -= var12;
            }

            float var8 = this.a(var5, var4, this.s);
            if (var7) {
               this.h += var12;
               this.i += var12;
            }

            if (this.r) {
               this.h += var12;
               if (var7) {
                  this.h -= var12;
                  this.i -= var12;
               }

               this.a(var5, var4, this.s);
               this.h -= var12;
               if (var7) {
                  this.h += var12;
                  this.i += var12;
               }

               var8++;
            }

            if (this.u) {
               bge var9 = bge.a;
               GL11.glDisable(3553);
               var9.b();
               var9.a(this.h, this.i + this.a / 2, 0.0);
               var9.a(this.h + var8, this.i + this.a / 2, 0.0);
               var9.a(this.h + var8, this.i + this.a / 2 - 1.0F, 0.0);
               var9.a(this.h, this.i + this.a / 2 - 1.0F, 0.0);
               var9.a();
               GL11.glEnable(3553);
            }

            if (this.t) {
               bge var14 = bge.a;
               GL11.glDisable(3553);
               var14.b();
               int var10 = this.t ? -1 : 0;
               var14.a(this.h + var10, this.i + this.a, 0.0);
               var14.a(this.h + var8, this.i + this.a, 0.0);
               var14.a(this.h + var8, this.i + this.a - 1.0F, 0.0);
               var14.a(this.h + var10, this.i + this.a - 1.0F, 0.0);
               var14.a();
               GL11.glEnable(3553);
            }

            this.h += (int)var8;
         }
      }
   }

   private int a(String var1, int var2, int var3, int var4, int var5, boolean var6) {
      if (this.k) {
         var1 = this.d(var1);
         int var7 = this.a(var1);
         var2 = var2 + var4 - var7;
      }

      return this.b(var1, var2, var3, var5, var6);
   }

   private int b(String var1, int var2, int var3, int var4, boolean var5) {
      if (var1 == null) {
         return 0;
      }

      if ((var4 & -67108864) == 0) {
         var4 |= -16777216;
      }

      if (var5) {
         var4 = (var4 & 16579836) >> 2 | var4 & 0xFF000000;
      }

      this.l = (var4 >> 16 & 0xFF) / 255.0F;
      this.m = (var4 >> 8 & 0xFF) / 255.0F;
      this.n = (var4 & 0xFF) / 255.0F;
      this.o = (var4 >> 24 & 0xFF) / 255.0F;
      GL11.glColor4f(this.l, this.m, this.n, this.o);
      this.h = var2;
      this.i = var3;
      this.a(var1, var5);
      return (int)this.h;
   }

   public int a(String var1) {
      if (var1 == null) {
         return 0;
      }

      int var2 = 0;
      boolean var3 = false;

      for (int var4 = 0; var4 < var1.length(); var4++) {
         char var5 = var1.charAt(var4);
         int var6 = this.a(var5);
         if (var6 < 0 && var4 < var1.length() - 1) {
            var5 = var1.charAt(++var4);
            if (var5 == 'l' || var5 == 'L') {
               var3 = true;
            } else if (var5 == 'r' || var5 == 'R') {
               var3 = false;
            }

            var6 = 0;
         }

         var2 += var6;
         if (var3) {
            var2++;
         }
      }

      return var2;
   }

   public int a(char var1) {
      if (var1 == 167) {
         return -1;
      } else if (var1 == ' ') {
         return 4;
      } else {
         int var2 = v.a.indexOf(var1);
         if (var2 >= 0 && !this.j) {
            return this.c[var2 + 32];
         } else if (this.d[var1] != 0) {
            int var3 = this.d[var1] >>> 4;
            int var4 = this.d[var1] & 15;
            return (var4 + 1 - var3) / 2 + 1;
         } else {
            return 0;
         }
      }
   }

   public String a(String var1, int var2) {
      return this.a(var1, var2, false);
   }

   public String a(String var1, int var2, boolean var3) {
      StringBuilder var4 = new StringBuilder();
      int var5 = 0;
      int var6 = var3 ? var1.length() - 1 : 0;
      int var7 = var3 ? -1 : 1;
      boolean var8 = false;
      boolean var9 = false;

      for (int var10 = var6; var10 >= 0 && var10 < var1.length() && var5 < var2; var10 += var7) {
         char var11 = var1.charAt(var10);
         int var12 = this.a(var11);
         if (var8) {
            var8 = false;
            if (var11 == 'l' || var11 == 'L') {
               var9 = true;
            } else if (var11 == 'r' || var11 == 'R') {
               var9 = false;
            }
         } else if (var12 < 0) {
            var8 = true;
         } else {
            var5 += var12;
            if (var9) {
               var5++;
            }
         }

         if (var5 > var2) {
            break;
         }

         if (var3) {
            var4.insert(0, var11);
         } else {
            var4.append(var11);
         }
      }

      return var4.toString();
   }

   private String e(String var1) {
      while (var1 != null && var1.endsWith("\n")) {
         var1 = var1.substring(0, var1.length() - 1);
      }

      return var1;
   }

   public void a(String var1, int var2, int var3, int var4, int var5) {
      this.e();
      this.p = var5;
      var1 = this.e(var1);
      this.c(var1, var2, var3, var4, false);
   }

   private void c(String var1, int var2, int var3, int var4, boolean var5) {
      for (Object var7 : this.c(var1, var4)) {
         String var8 = (String)var7;
         this.a(var8, var2, var3, var4, this.p, var5);
         var3 += this.a;
      }
   }

   public int b(String var1, int var2) {
      return this.a * this.c(var1, var2).size();
   }

   public void a(boolean var1) {
      this.j = var1;
   }

   public boolean b() {
      return this.j;
   }

   public void b(boolean var1) {
      this.k = var1;
   }

   public List c(String var1, int var2) {
      return Arrays.asList(this.d(var1, var2).split("\n"));
   }

   String d(String var1, int var2) {
      int var3 = this.e(var1, var2);
      if (var1.length() <= var3) {
         return var1;
      }

      String var4 = var1.substring(0, var3);
      char var5 = var1.charAt(var3);
      boolean var6 = var5 == ' ' || var5 == '\n';
      String var7 = f(var4) + var1.substring(var3 + (var6 ? 1 : 0));
      return var4 + "\n" + this.d(var7, var2);
   }

   private int e(String var1, int var2) {
      int var3 = var1.length();
      int var4 = 0;
      int var5 = 0;
      int var6 = -1;
      boolean var7 = false;

      while (var5 < var3) {
         char var8 = var1.charAt(var5);
         switch (var8) {
            case '\n':
               var5--;
               break;
            case ' ':
               var6 = var5;
            default:
               var4 += this.a(var8);
               if (var7) {
                  var4++;
               }
               break;
            case '§':
               if (var5 < var3 - 1) {
                  char var9 = var1.charAt(++var5);
                  if (var9 == 'l' || var9 == 'L') {
                     var7 = true;
                  } else if (var9 == 'r' || var9 == 'R' || b(var9)) {
                     var7 = false;
                  }
               }
         }

         if (var8 == '\n') {
            var6 = ++var5;
            break;
         }

         if (var4 > var2) {
            break;
         }

         var5++;
      }

      return var5 != var3 && var6 != -1 && var6 < var5 ? var6 : var5;
   }

   private static boolean b(char var0) {
      return var0 >= '0' && var0 <= '9' || var0 >= 'a' && var0 <= 'f' || var0 >= 'A' && var0 <= 'F';
   }

   private static boolean c(char var0) {
      return var0 >= 'k' && var0 <= 'o' || var0 >= 'K' && var0 <= 'O' || var0 == 'r' || var0 == 'R';
   }

   private static String f(String var0) {
      String var1 = "";
      int var2 = -1;
      int var3 = var0.length();

      while ((var2 = var0.indexOf(167, var2 + 1)) != -1) {
         if (var2 < var3 - 1) {
            char var4 = var0.charAt(var2 + 1);
            if (b(var4)) {
               var1 = "§" + var4;
            } else if (c(var4)) {
               var1 = var1 + "§" + var4;
            }
         }
      }

      return var1;
   }

   public boolean c() {
      return this.k;
   }
}
