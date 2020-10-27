using System.Linq;
using UnityEditor;
using UnityEngine;

namespace UniLib.Scripts.Editor
{
    internal static class ExportLib
    {
        [MenuItem("UniLib/Export Android")]
        public static void Build()
        {
            // Gather values from project
            var scenes = EditorBuildSettings.scenes.Where(scene => scene.enabled).Select(s => s.path).ToArray();

            // Define BuildPlayer Options
            var buildOptions = new BuildPlayerOptions
            {
                scenes = scenes,
                locationPathName = "Build",
                target = BuildTarget.Android
            };

            // Perform build
            var buildReport = BuildPipeline.BuildPlayer(buildOptions);

            // Summary
            var summary = buildReport.summary;
            Debug.Log(summary);

            // Result
            var result = summary.result;
            Debug.Log(result);
        }
    }
}