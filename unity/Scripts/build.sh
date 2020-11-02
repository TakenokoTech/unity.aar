echo "================================"
echo " Build Unity Project"
echo "================================"
cd unity
/Volumes/Transcend/Unity/2019.4.13f1/Unity.app/Contents/MacOS/Unity \
-batchmode \
-logfile - \
-projectPath . \
-quit \
-buildTarget Android \
-customBuildName Android \
-customBuildTarget Android \
-customBuildPath Build \
-executeMethod UniLib.Scripts.Editor.ExportLib.Build

echo ""
echo "================================"
echo " Build ARR"
echo "================================"
cd Build
../../android/gradlew build

echo ""
echo "================================"
echo " Build APK"
echo "================================"
cd ../../android
./gradlew build
