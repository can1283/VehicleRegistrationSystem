import React, { useState } from "react";
import axios from "axios";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { Link, useNavigate } from "react-router-dom";
import { IoEyeSharp } from "react-icons/io5";
import { BsFillEyeSlashFill } from "react-icons/bs";

const ChangePass = () => {
    const [currentPassword, setCurrentPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [showCurrentPassword, setShowCurrentPassword] = useState(false);
    const [showNewPassword, setShowNewPassword] = useState(false);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);
    const navigation = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const payload = {
            currentPassword: currentPassword,
            newPassword: newPassword,
        };

        try {
            const userId = localStorage.getItem("id");
            await axios.post(`http://localhost:8080/api/auth/change-password/${userId}`, payload);
            navigation("/dashboard");
            setSuccess(true);
        } catch (e) {
            setError(e.response.data.error);
        }
    };

    return (
        <>
            <div className="bg-gradient-to-t from-blue-700 to-blue-900 w-full h-full absolute"></div>
            <div className="absolute flex items-center justify-center w-full h-full">
                <div className="bg-white shadow-2xl border border-2 rounded-2xl px-10 py-8">
                    <h2 className="text-2xl font-semibold mb-4">Changing Password</h2>
                    <Formik
                        initialValues={{
                            currentPassword: "",
                            newPassword: "",
                        }}
                        validationSchema={Yup.object({
                            currentPassword: Yup.string()
                                .required("Password is required")
                                .min(8, "Password must be at least 8 characters")
                                .max(16, "Password must be at most 16 characters")
                                .matches(
                                    /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/,
                                    "Password is not valid"
                                ),
                            newPassword: Yup.string()
                                .required("Password is required")
                                .min(8, "Password must be at least 8 characters")
                                .max(16, "Password must be at most 16 characters")
                                .matches(
                                    /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/,
                                    "Password is not valid"
                                ),
                        })}
                        onSubmit={async (values, { setSubmitting, setErrors, setStatus }) => {
                            const userId = localStorage.getItem("id");
                            const payload = {
                                currentPassword: values.currentPassword,
                                newPassword: values.newPassword,
                            };

                            try {
                                await axios.post(`http://localhost:8080/api/auth/change-password/${userId}`, payload);
                                setStatus({ success: "The password has been changed successfully." });
                            } catch (e) {
                                setStatus({ error: e.response.data.message });
                                setSubmitting(false);
                            }
                        }}
                    >
                        {({ status }) => (
                            <Form>
                                <div className="mb-4">
                                    <label htmlFor="currentPassword" className="block font-medium text-gray-700">
                                        Current Password
                                    </label>
                                    <div className="relative">
                                        <Field
                                            type={showCurrentPassword ? "text" : "password"}
                                            id="currentPassword"
                                            name="currentPassword"
                                            className="p-2 border border-gray-300 rounded-xl outline-none w-full"
                                        />
                                        <span
                                            className="absolute right-4 top-4 cursor-pointer"
                                            onClick={() => setShowCurrentPassword(!showCurrentPassword)}
                                        >
                                            {showCurrentPassword ? <IoEyeSharp /> : <BsFillEyeSlashFill />}
                                        </span>
                                    </div>
                                    <ErrorMessage name="currentPassword" component="div" className="text-red-500" />
                                </div>
                                <div className="mb-4">
                                    <label htmlFor="newPassword" className="block font-medium text-gray-700">
                                        New Password
                                    </label>
                                    <div className="relative">
                                        <Field
                                            type={showNewPassword ? "text" : "password"}
                                            id="newPassword"
                                            name="newPassword"
                                            className="p-2 border border-gray-300 rounded-xl outline-none w-full"
                                        />
                                        <span
                                            className="absolute right-4 top-4 cursor-pointer"
                                            onClick={() => setShowNewPassword(!showNewPassword)}
                                        >
                                            {showNewPassword ? <IoEyeSharp /> : <BsFillEyeSlashFill />}
                                        </span>
                                    </div>
                                    <ErrorMessage name="newPassword" component="div" className="text-red-500" />
                                </div>
                                {status && status.error && <p className="text-red-500">{status.error}</p>}
                                {status && status.success && <p className="text-green-500 mb-4 text-lg">{status.success}</p>}
                                <div className="flex justify-between mt-8">
                                    <button
                                        type="submit"
                                        className="px-4 py-2 bg-indigo-700 text-white rounded-xl outline-0"
                                    >
                                        Change Password
                                    </button>
                                    <Link to="/dashboard">
                                        <button className="px-4 ml-4 py-2 bg-indigo-500 text-white rounded-xl outline-0">
                                            Back
                                        </button>
                                    </Link>
                                </div>
                            </Form>
                        )}
                    </Formik>
                </div>
            </div>
        </>
    );
};

export default ChangePass;
