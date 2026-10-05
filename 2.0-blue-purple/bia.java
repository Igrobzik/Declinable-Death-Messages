import org.lwjgl.opengl.GL11;

public class bia extends bhf {
   public void a(nm var1, double var2, double var4, double var6, float var8, float var9) {
      GL11.glPushMatrix();
      short var10 = 240;
      short var11 = 240;
      bkv.a(bkv.b, var10 / 1.0F, var11 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-this.b.j, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(this.b.k, 1.0F, 0.0F, 0.0F);
      GL11.glTranslatef(-0.3F, 0.9F, 0.0F);
      float var12 = 0.03125F;
      GL11.glScalef(-var12, -var12, var12);
      GL11.glDisable(2896);
      GL11.glDepthMask(false);
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glDisable(2884);
      GL11.glDisable(3042);
      bgj var13 = bgj.a;
      String var14 = bp.a().a(var1.c());
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3553);
      var13.b();
      awz var15 = this.b.a();
      int var16 = var15.a(var14);
      var13.a(1.0F, 1.0F, 1.0F, 0.5F);
      var13.a(-1.0, -1.0, 0.0);
      var13.a(-1.0, 8.0, 0.0);
      var13.a(var16 + 1, 8.0, 0.0);
      var13.a(var16 + 1, -1.0, 0.0);
      var13.a();
      var13.b();
      var13.a(1.0F, 1.0F, 1.0F, 1.0F);
      var13.a(0.0, 8.0, 0.0);
      var13.a(-3.0, 11.0, 0.0);
      var13.a(-2.0, 11.0, 0.0);
      var13.a(4.0, 8.0, 0.0);
      var13.a();
      GL11.glEnable(3553);
      GL11.glEnable(2896);
      GL11.glEnable(2884);
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      var15.b(var14, 0, 0, 553648127);
      GL11.glDepthMask(true);
      var15.b(var14, 0, 0, -16777216);
      GL11.glEnable(2896);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(2929);
      GL11.glPopMatrix();
   }
   public void a(mp var1, double var2, double var4, double var6, float var8, float var9) {
      this.a((nm)var1, var2, var4, var6, var8, var9);
   }
}
