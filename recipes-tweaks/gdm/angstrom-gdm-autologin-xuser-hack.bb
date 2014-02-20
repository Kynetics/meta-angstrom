DESCRIPTION = "Append Autologin to GDM config (custom.conf)"
SUMMARY = "A hack to enable automatic login for the root using when using the GDM display manager"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PACKAGE_ARCH = "all"
ALLOW_EMPTY_${PN} = "1"

pkg_postinst_${PN}() {
#!/bin/sh
grep "TimedLogin" $D/etc/gdm/custom.conf
if [ $? -eq 0 ]; then
    echo "NOTE:: custom.conf already has 'TimedLogin' configured - not patching"
else
    cat >> $D/etc/gdm/custom.conf <<EOF

[daemon]
TimedLoginEnable=true
TimedLogin=xuser
TimedLoginDelay=10

EOF
fi
}

RDEPENDS_${PN} = "gdm"