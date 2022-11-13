// Copyright Epic Games, Inc. All Rights Reserved.
/*===========================================================================
	Generated code exported from UnrealHeaderTool.
	DO NOT modify this manually! Edit the corresponding .h files instead!
===========================================================================*/

#include "UObject/GeneratedCppIncludes.h"
#include "FPSTest/FPSTestGameMode.h"
PRAGMA_DISABLE_DEPRECATION_WARNINGS
void EmptyLinkFunctionForGeneratedCodeFPSTestGameMode() {}
// Cross Module References
	FPSTEST_API UClass* Z_Construct_UClass_AFPSTestGameMode_NoRegister();
	FPSTEST_API UClass* Z_Construct_UClass_AFPSTestGameMode();
	ENGINE_API UClass* Z_Construct_UClass_AGameModeBase();
	UPackage* Z_Construct_UPackage__Script_FPSTest();
// End Cross Module References
	void AFPSTestGameMode::StaticRegisterNativesAFPSTestGameMode()
	{
	}
	IMPLEMENT_CLASS_NO_AUTO_REGISTRATION(AFPSTestGameMode);
	UClass* Z_Construct_UClass_AFPSTestGameMode_NoRegister()
	{
		return AFPSTestGameMode::StaticClass();
	}
	struct Z_Construct_UClass_AFPSTestGameMode_Statics
	{
		static UObject* (*const DependentSingletons[])();
#if WITH_METADATA
		static const UECodeGen_Private::FMetaDataPairParam Class_MetaDataParams[];
#endif
		static const FCppClassTypeInfoStatic StaticCppClassTypeInfo;
		static const UECodeGen_Private::FClassParams ClassParams;
	};
	UObject* (*const Z_Construct_UClass_AFPSTestGameMode_Statics::DependentSingletons[])() = {
		(UObject* (*)())Z_Construct_UClass_AGameModeBase,
		(UObject* (*)())Z_Construct_UPackage__Script_FPSTest,
	};
#if WITH_METADATA
	const UECodeGen_Private::FMetaDataPairParam Z_Construct_UClass_AFPSTestGameMode_Statics::Class_MetaDataParams[] = {
		{ "HideCategories", "Info Rendering MovementReplication Replication Actor Input Movement Collision Rendering HLOD WorldPartition DataLayers Transformation" },
		{ "IncludePath", "FPSTestGameMode.h" },
		{ "ModuleRelativePath", "FPSTestGameMode.h" },
		{ "ShowCategories", "Input|MouseInput Input|TouchInput" },
	};
#endif
	const FCppClassTypeInfoStatic Z_Construct_UClass_AFPSTestGameMode_Statics::StaticCppClassTypeInfo = {
		TCppClassTypeTraits<AFPSTestGameMode>::IsAbstract,
	};
	const UECodeGen_Private::FClassParams Z_Construct_UClass_AFPSTestGameMode_Statics::ClassParams = {
		&AFPSTestGameMode::StaticClass,
		"Game",
		&StaticCppClassTypeInfo,
		DependentSingletons,
		nullptr,
		nullptr,
		nullptr,
		UE_ARRAY_COUNT(DependentSingletons),
		0,
		0,
		0,
		0x008802ACu,
		METADATA_PARAMS(Z_Construct_UClass_AFPSTestGameMode_Statics::Class_MetaDataParams, UE_ARRAY_COUNT(Z_Construct_UClass_AFPSTestGameMode_Statics::Class_MetaDataParams))
	};
	UClass* Z_Construct_UClass_AFPSTestGameMode()
	{
		if (!Z_Registration_Info_UClass_AFPSTestGameMode.OuterSingleton)
		{
			UECodeGen_Private::ConstructUClass(Z_Registration_Info_UClass_AFPSTestGameMode.OuterSingleton, Z_Construct_UClass_AFPSTestGameMode_Statics::ClassParams);
		}
		return Z_Registration_Info_UClass_AFPSTestGameMode.OuterSingleton;
	}
	template<> FPSTEST_API UClass* StaticClass<AFPSTestGameMode>()
	{
		return AFPSTestGameMode::StaticClass();
	}
	DEFINE_VTABLE_PTR_HELPER_CTOR(AFPSTestGameMode);
	struct Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestGameMode_h_Statics
	{
		static const FClassRegisterCompiledInInfo ClassInfo[];
	};
	const FClassRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestGameMode_h_Statics::ClassInfo[] = {
		{ Z_Construct_UClass_AFPSTestGameMode, AFPSTestGameMode::StaticClass, TEXT("AFPSTestGameMode"), &Z_Registration_Info_UClass_AFPSTestGameMode, CONSTRUCT_RELOAD_VERSION_INFO(FClassReloadVersionInfo, sizeof(AFPSTestGameMode), 596631669U) },
	};
	static FRegisterCompiledInInfo Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestGameMode_h_13043082(TEXT("/Script/FPSTest"),
		Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestGameMode_h_Statics::ClassInfo, UE_ARRAY_COUNT(Z_CompiledInDeferFile_FID_FPSTest_Source_FPSTest_FPSTestGameMode_h_Statics::ClassInfo),
		nullptr, 0,
		nullptr, 0);
PRAGMA_ENABLE_DEPRECATION_WARNINGS
